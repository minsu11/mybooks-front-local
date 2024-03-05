package store.mybooks.front.auth.aop;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import store.mybooks.front.auth.error.ErrorMessage;
import store.mybooks.front.auth.exception.AccessIdForbiddenException;
import store.mybooks.front.auth.exception.AuthenticationIsNotValidException;
import store.mybooks.front.auth.exception.StatusIsNotActiveException;
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
@Slf4j
public class AuthorizationAspect {

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
            log.warn(error);

            if (ErrorMessage.INVALID_ACCESS.getMessage().contains(Objects.requireNonNull(error))) { // 권한이 없음
                throw new AccessIdForbiddenException(); // 인덱스로 보내기
            } else if (error.contains(ErrorMessage.TOKEN_EXPIRED.getMessage())) { // 토큰만료 재발급 받고 다시 부르기
                // 리프래시 토큰이 만료면 throw Authentication -> 로그인하세요
                // 리프래시 토큰 만료 아니면 토큰은 멀쩡하니 다시부르면 원래 요청대로 가짐
                joinPoint.proceed();
            } else if (error.contains(ErrorMessage.INVALID_TOKEN.getMessage())) { // 토큰위조됨 쿠키삭제하기
                CookieUtils.deleteJwtCookie(Objects.requireNonNull(response));
                throw new AuthenticationIsNotValidException();
            } else if (error.contains(ErrorMessage.INACTIVE_USER.getMessage())) { // 활성상태가 아님 -> 인증사이트로
                throw new StatusIsNotActiveException();
            }

            throw e; // 다른 에러인 경우 = 토큰관련 에러가 아닌경우 그대로 Exception 던진다
        }


    }


}
