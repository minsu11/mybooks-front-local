package store.mybooks.front.order.exception;

/**
 * packageName    : store.mybooks.front.order.exception<br>
 * fileName       : AmountOverStockException<br>
 * author         : minsu11<br>
 * date           : 3/21/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/21/24        minsu11       최초 생성<br>
 */
public class AmountOverStockException extends RuntimeException {
    public AmountOverStockException() {
        super("재고가 부족합니다.");
    }
}
