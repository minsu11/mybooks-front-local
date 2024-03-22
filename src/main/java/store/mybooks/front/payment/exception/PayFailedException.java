package store.mybooks.front.payment.exception;

/**
 * packageName    : store.mybooks.front.payment.exception<br>
 * fileName       : PayFailedException<br>
 * author         : minsu11<br>
 * date           : 3/19/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/19/24        minsu11       최초 생성<br>
 */
public class PayFailedException extends RuntimeException {
    public PayFailedException() {
        super("결제 실패");
    }
}
