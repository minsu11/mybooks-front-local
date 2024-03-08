package store.mybooks.front.auth.exception;

/**
 * packageName    : store.mybooks.front.auth.exception<br>
 * fileName       : TokenExpiredException<br>
 * author         : masiljangajji<br>
 * date           : 3/8/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/8/24        masiljangajji       최초 생성
 */
public class TokenExpiredException extends RuntimeException{

    public TokenExpiredException() {
        super("토큰이 만료됐습니다");
    }
}
