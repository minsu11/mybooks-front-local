package store.mybooks.front.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.mybooks.front.config.CookieConfig;


/**
 * packageName    : store.mybooks.front.utils<br>
 * fileName       : CookieUtils<br>
 * author         : masiljangajji<br>
 * date           : 3/5/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/5/24        masiljangajji       최초 생성
 */


@Component
@RequiredArgsConstructor
public class CookieUtils {

    private static CookieConfig cookieConfig = null;

    @Autowired
    public CookieUtils(CookieConfig cookieConfig) {
        CookieUtils.cookieConfig = cookieConfig;
    }

    public static void addJwtCookie(HttpServletResponse response, String token) {

        Cookie cookie= new Cookie("identity_cookie",token);
        cookie.setHttpOnly(true);
        cookie.setDomain(cookieConfig.getDomain());
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
    }

    public static void addAdminCookie(HttpServletResponse response) {

        Cookie cookie= new Cookie("admin_cookie","admin");
        cookie.setHttpOnly(true);
        cookie.setDomain(cookieConfig.getDomain());
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
    }

    public static void deleteJwtCookie(HttpServletResponse response) {

        Cookie cookie= new Cookie("identity_cookie",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public static void deleteAdminCookie(HttpServletResponse response) {

        Cookie cookie= new Cookie("admin_cookie",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public static String getIdentityCookieValue(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("identity_cookie".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static String getAdminCookieValue(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("admin_cookie".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
