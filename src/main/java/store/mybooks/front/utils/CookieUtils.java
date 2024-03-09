package store.mybooks.front.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class CookieUtils {

    public static void addJwtCookie(HttpServletResponse response, String token) {

        response.setHeader("Set-Cookie",
                "identity_cookie=" + token + "; " +
                        "Path=/; " + // 적용될 범위
                        "Domain=localhost; " + // 적용될 도메인
                        "HttpOnly; " + // JavaScript에서 쿠키에 접근하는 것을 방지하기 위해 HttpOnly 속성을 설정합니다.
                        "Max-Age=604800; " + // 쿠키 생존시간
                        "SameSite=Strict; " +
                        // SameSite 설정 (Strict, Lax, None 중 선택) Strict 쿠키가 같은 도메인에서만 헤더로 넘어감, 요청보낼떄는 헤더에 담아 보낼꺼임
                        "Secure" // Secure 설정
        );
    }

    public static void deleteJwtCookie(HttpServletResponse response) {
        response.setHeader("Set-Cookie",
                "identity_cookie=; " +
                        "Path=/; " +
                        "Domain=localhost; " +
                        "Max-Age=0; " + // 쿠키를 즉시 만료시킵니다.
                        "SameSite=Strict; " +
                        "Secure");
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

}
