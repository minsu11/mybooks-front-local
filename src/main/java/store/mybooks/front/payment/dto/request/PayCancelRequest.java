package store.mybooks.front.payment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.payment.dto.request<br>
 * fileName       : PayCanecelRequest<br>
 * author         : minsu11<br>
 * date           : 3/23/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/23/24        minsu11       최초 생성<br>
 */
@Getter
@AllArgsConstructor
public class PayCancelRequest {
    private String paymentKey;
    private String orderNumber;
    private String status;
    private Integer totalAmount;
    private String requestedAt;
}
