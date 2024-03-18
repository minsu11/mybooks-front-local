package store.mybooks.front.payment.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.payment.dto.request<br>
 * fileName       : TossPaymentRequest<br>
 * author         : minsu11<br>
 * date           : 3/18/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/18/24        minsu11       최초 생성<br>
 */
@Getter
@NoArgsConstructor
public class TossPaymentRequest {
    private String paymentKey;
    private String orderId;
    private String amount;


}
