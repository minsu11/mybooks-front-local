package store.mybooks.front.auth.exception;

/**
 * packageName    : store.mybooks.front.auth.exception<br>
 * fileName       : PasswordNotValidException<br>
 * author         : masiljangajji<br>
 * date           : 3/24/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/24/24        masiljangajji       최초 생성
 */
public class PasswordNotValidException extends RuntimeException{
    public PasswordNotValidException() {
        super("비밀번호가 일치하지 않습니다");
    }
}
