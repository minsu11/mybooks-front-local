package store.mybooks.front.home;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.utils.CookieUtils;

/**
 * packageName    : store.mybooks.front
 * fileName       : HomeController
 * author         : masiljangajji
 * date           : 2/26/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/26/24        masiljangajji       최초 생성
 */

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(HttpServletRequest request) {
        request.setAttribute("identity_cookie_value", CookieUtils.getIdentityCookieValue(request));
        return "index";
    }

}
