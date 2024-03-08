package store.mybooks.front.auth.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import store.mybooks.front.auth.adaptor.TokenAdaptor;
import store.mybooks.front.auth.dto.request.LogoutRequest;
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

@Component
@RequiredArgsConstructor
public class LogoutInterceptor implements HandlerInterceptor {

    private final TokenAdaptor tokenAdaptor;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {

        // 리프래시토큰 삭제
        tokenAdaptor.deleteRefreshToken(new LogoutRequest(CookieUtils.getIdentityCookieValue(request)));
        // 쿠키 삭제
        CookieUtils.deleteJwtCookie(response);

    }
}
