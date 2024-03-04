package store.mybooks.front.utils;

import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import store.mybooks.front.auth.exception.AuthenticationIsNotValidException;

/**
 * packageName    : store.mybooks.front.util<br>
 * fileName       : Util<br>
 * author         : minsu11<br>
 * date           : 2/27/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/27/24        minsu11       최초 생성<br>
 */
@Slf4j
public class Utils {

    private Utils() {
    }

    /**
     * methodName : getHttpHeader<br>
     * author : minsu11<br>
     * description : 중복된 {@code Http Hearder} 생성 부분을 메서드로 분리.
     * 매개변수에 아무것도 넣지 않으면 기본적으로 {@code Json}으로 설정.
     * <br> *
     *
     * @return http headers
     */
    public static HttpHeaders getHttpHeader() {
        return getHttpHeader(MediaType.APPLICATION_JSON, List.of(MediaType.APPLICATION_JSON));
    }

    /**
     * methodName : getHttpHeader<br>
     * author : minsu11<br>
     * description : 중복된 {@code Http Hearder} 생성 부분을 메서드로 분리.
     * {@code mediaType}으로 설정.
     * <br> *
     *
     * @param mediaType 요청과 응답을 받을 타입
     * @return http headers
     */
    public static HttpHeaders getHttpHeader(MediaType mediaType) {
        return getHttpHeader(mediaType, List.of(mediaType));
    }


    /**
     * methodName : getHttpHeader<br>
     * author : minsu11<br>
     * description : 중복된 {@code Http Hearder} 생성 부분을 메서드로 분리.
     * <br> *
     *
     * @param mediaType     요청 받을 타입
     * @param mediaTypeList 응답 받을 타입
     * @return http headers
     */
    public static HttpHeaders getHttpHeader(MediaType mediaType, List<MediaType> mediaTypeList) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setAccept(mediaTypeList);
        return headers;
    }

    public static HttpHeaders addAuthHeader(HttpServletRequest request) { // todo addHeader 이름변경하기

        HttpHeaders headers = Utils.getHttpHeader();
        String token = Utils.getIdentityCookieValue(request.getCookies());
        headers.set("Authorization", token);
        return headers;
    }


    /**
     * methodName : getResponseEntity<br>
     * author : minsu11<br>
     * description : 중복 코드
     * <br> *
     *
     * @param <T>    the type parameter
     * @param status 응답 받을 {@code Http status code}
     * @return 반환 받을 타입 데이터
     */
    public static <T> T getResponseEntity(ResponseEntity<T> exchange, HttpStatus status) {

        if (exchange.getStatusCode() != status) {
            throw new RuntimeException();
        }

        return exchange.getBody();
    }

    public static String getIdentityCookieValue(Cookie[] cookies) {

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("identity_cookie".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        throw new AuthenticationIsNotValidException();
    }


    

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


}