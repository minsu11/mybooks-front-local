package store.mybooks.front.utils.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.auth<br>
 * fileName       : CookieInterceptor<br>
 * author         : masiljangajji<br>
 * date           : 3/5/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/5/24        masiljangajji       최초 생성
 */


public class CookieInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        System.out.println("요청 URI: " + request.getRequestURI());
        // 컨트롤러 부를때 한번 부름 ,  /user/asserts/images/logo/white-logo .jpg .svg
        // 얘들 이름 바꿔주면 됨
        request.setAttribute("identity_cookie_value", Utils.getIdentityCookieValue(request));

        return true; // 요청 계속 진행
    }


}