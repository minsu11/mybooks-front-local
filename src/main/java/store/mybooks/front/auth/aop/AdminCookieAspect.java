package store.mybooks.front.auth.aop;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import store.mybooks.front.auth.exception.AccessIdForbiddenException;
import store.mybooks.front.auth.redis.RedisAuthService;
import store.mybooks.front.utils.Utils;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class AdminCookieAspect {

    private final RedisAuthService redisAuthService;

    /**
     * methodName : beforeMethod
     * author : masiljangajji
     * description : 어드민 페이지에 접근시 RequiredAdminCookie 어노테이션이 있는 경우 , adminCookie 를 확인 및 검증하는 인가처리
     */
    @Before("@annotation(store.mybooks.front.auth.Annotation.RequiredAdminCookie)")
    public void beforeMethod() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(
                RequestContextHolder.getRequestAttributes())).getRequest();

        String adminCookieValue = (String) request.getAttribute("admin_cookie_value");

        // 쿠키가 있고
        if (Objects.nonNull(adminCookieValue)) {
            // 레디스에서 adminCookie 로 뽑은 Value 가 내가 쓰고있는 ip 주소 , user agent 랑 맞는경우 패스
            if (Objects.equals(redisAuthService.getValues(adminCookieValue),
                    Utils.getUserIp(request) + Utils.getUserAgent(request))) {
                return;
            }
        }
        throw new AccessIdForbiddenException();

    }
}
