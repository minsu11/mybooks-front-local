package store.mybooks.front.auth.aop;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import store.mybooks.front.auth.adaptor.TokenAdaptor;
import store.mybooks.front.auth.dto.request.RefreshTokenRequest;
import store.mybooks.front.auth.dto.response.RefreshTokenResponse;
import store.mybooks.front.auth.error.ErrorMessage;
import store.mybooks.front.auth.exception.AccessIdForbiddenException;
import store.mybooks.front.auth.exception.AuthenticationIsNotValidException;
import store.mybooks.front.auth.exception.StatusIsDormancyException;
import store.mybooks.front.auth.exception.StatusIsLockException;
import store.mybooks.front.auth.exception.TokenExpiredException;
import store.mybooks.front.auth.redis.RedisAuthService;
import store.mybooks.front.config.RedisProperties;
import store.mybooks.front.utils.CookieUtils;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.auth.aop<br>
 * fileName       : AuthAop<br>
 * author         : masiljangajji<br>
 * date           : 3/2/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/2/24        masiljangajji       최초 생성
 */
@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorizationAspect {

    private final TokenAdaptor tokenAdaptor;

    private final RedisAuthService redisAuthService;

    private final RedisProperties redisProperties;

    /**
     * methodName : aroundMethod
     * author : masiljangajji
     * description : RequiredAuthorization 어노테이션이 걸려있는 경우 , 회원의 인가처리가 필요하다는 것을 의미
     * identity-cookie 라는 이름의 쿠키에 JWT 를 담고 있기 떄문에 Header 에 토큰으 담아서 gateway 로 보냄
     * gateway 에서 토큰 검증 및 유저 검증 인가처리에 문제가 없을 시 그대로 return
     * Exception 이 발생한다면 (토큰 검증 실패 , 토큰만료 , 일반 유저가 어드민이 사용하는 기능접근 ,유저 휴면상태 , 유저 잠금상태) ErrorMessage 를 이용해
     * 각각의 상황에 맞는 처리를 함 
     *
     * @param joinPoint point
     * @return object
     * @throws Throwable the throwable
     */
    @Around(value = "@annotation(store.mybooks.front.auth.Annotation.RequiredAuthorization)")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(
                RequestContextHolder.getRequestAttributes())).getRequest();

        HttpServletResponse response =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        RequestContextHolder.currentRequestAttributes()
                .setAttribute("authHeader", Utils.addAuthHeader(request), RequestAttributes.SCOPE_REQUEST);

        try {
            return joinPoint.proceed();
        } catch (RuntimeException e) {

            String error = e.getMessage();
            log.error("aop fin:" + error);

            if (error.contains(ErrorMessage.INVALID_ACCESS.getMessage())) { // 권한이 없음
                throw new AccessIdForbiddenException(); // 인덱스로 보내기
            } else if (error.contains(ErrorMessage.TOKEN_EXPIRED.getMessage())) { // 토큰만료 재발급 받고 다시 부르기

                // 토큰을 갱신하는 요청을 보냄 (기존 엑세스 토큰을 보냄)
                RefreshTokenResponse refreshTokenResponse =
                        tokenAdaptor.refreshAccessToken(
                                new RefreshTokenRequest((String) request.getAttribute("identity_cookie_value"),
                                        Utils.getUserIp(request), Utils.getUserAgent(request)));

                // 리프래시 토큰 만료 됐거나 유효하지않음
                if (Objects.isNull(refreshTokenResponse.getAccessToken())) {
                    throw new TokenExpiredException();
                }

                // 쿠키에 재발급한 엑세스토큰 넣어주고
                CookieUtils.addJwtCookie(Objects.requireNonNull(response), refreshTokenResponse.getAccessToken());
                // 헤더 설정해주고 기존 메서드 다시 불러
                RequestContextHolder.currentRequestAttributes()
                        .setAttribute("authHeader", Utils.refreshAuthHeader(refreshTokenResponse.getAccessToken()),
                                RequestAttributes.SCOPE_REQUEST);
                // 어드민 쿠키를 체크하는 redis 만료시간 재설정
                String adminCookieValue = (String) request.getAttribute("admin_cookie_value");
                if (Objects.nonNull(adminCookieValue)) {
                    redisAuthService.expireValues(adminCookieValue, redisProperties.getAdminExpiration());
                    // 쿠키 만료시간 재설정
                    CookieUtils.addAdminCookie(response, adminCookieValue);
                }
                return joinPoint.proceed();
            } else if (error.contains(ErrorMessage.INVALID_TOKEN.getMessage())) { // 토큰위조됨 쿠키삭제
                throw new AuthenticationIsNotValidException();
            } else if (error.contains(ErrorMessage.STATUS_IS_DORMANT_EXCEPTION.getMessage())) { // 휴면상태 -> 휴면인증사이트로
                throw new StatusIsDormancyException();
            } else if (error.contains(ErrorMessage.STATUS_IS_LOCK_EXCEPTION.getMessage())) { // 잠금상태 -> 잠금인증 페이지로
                throw new StatusIsLockException();
            }

            throw e; // 다른 에러인 경우 = 토큰관련 에러가 아닌경우 그대로 Exception 던진다
        }

    }


}
