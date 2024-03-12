package store.mybooks.front.auth.interceptor;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import store.mybooks.front.auth.adaptor.TokenAdaptor;
import store.mybooks.front.auth.dto.request.LogoutRequest;
import store.mybooks.front.auth.redis.RedisAuthService;
import store.mybooks.front.utils.CookieUtils;

/**
 * packageName    : store.mybooks.front.auth.interceptor<br>
 * fileName       : LogoutInterceptor<br>
 * author         : masiljangajji<br>
 * date           : 3/6/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/6/24        masiljangajji       최초 생성
 */

@Slf4j
public class LogoutInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {

        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        TokenAdaptor tokenAdaptor = Objects.requireNonNull(context).getBean(TokenAdaptor.class);

        //리프래시토큰 삭제
        tokenAdaptor.deleteRefreshToken(new LogoutRequest((String) request.getAttribute("identity_cookie_value"),request.getHeader("X-Forwarded-For"),request.getHeader("User-Agent")));

        // UUID - UserId 담은 redis 삭제 및 admin 쿠키 삭제
        if (Objects.nonNull(request.getAttribute("admin_cookie_value"))) {
            RedisAuthService redisAuthService = context.getBean(RedisAuthService.class);
            redisAuthService.deleteValues((String) request.getAttribute("admin_cookie_value"));
            CookieUtils.deleteAdminCookie(response);
        }

        // 엑세스 토큰 담은 쿠키 삭제
        CookieUtils.deleteJwtCookie(response);
        log.info("완전삭제");

    }
}
