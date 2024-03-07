package store.mybooks.front.auth.exception;

/**
 * packageName    : store.mybooks.front.auth.exception<br>
 * fileName       : NotLoginException<br>
 * author         : masiljangajji<br>
 * date           : 3/2/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/2/24        masiljangajji       최초 생성
 */
public class AuthenticationIsNotValidException extends RuntimeException{
    public AuthenticationIsNotValidException() {
        super("로그인 인증이 필요합니다");
    }
}
