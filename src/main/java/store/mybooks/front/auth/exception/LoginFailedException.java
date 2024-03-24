package store.mybooks.front.auth.exception;

/**
 * packageName    : store.mybooks.front.auth.exception<br>
 * fileName       : LoginFailedException<br>
 * author         : masiljangajji<br>
 * date           : 3/24/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/24/24        masiljangajji       최초 생성
 */
public class LoginFailedException extends RuntimeException{
    public LoginFailedException(String message) {
        super(message);
    }
}
