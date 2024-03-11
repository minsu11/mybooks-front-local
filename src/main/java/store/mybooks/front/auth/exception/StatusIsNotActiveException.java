package store.mybooks.front.auth.exception;

/**
 * packageName    : store.mybooks.front.auth.exception<br>
 * fileName       : StatusIsNoeActiceException<br>
 * author         : masiljangajji<br>
 * date           : 3/3/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/3/24        masiljangajji       최초 생성
 */
public class StatusIsNotActiveException extends RuntimeException{
    public StatusIsNotActiveException() {
        super("활성 상태가 아닙니다");
    }
}
