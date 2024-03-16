package store.mybooks.front.order.exception;

/**
 * packageName    : store.mybooks.front.order.exception<br>
 * fileName       : OrderModulationException<br>
 * author         : minsu11<br>
 * date           : 3/16/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/16/24        minsu11       최초 생성<br>
 */

public class OrderInfoNotMatchException extends RuntimeException {
    public OrderInfoNotMatchException() {
        super("주문 정보의 내용이 변조");
    }

    public OrderInfoNotMatchException(String message) {
        super(message);
    }
}
