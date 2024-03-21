package store.mybooks.front.payment.dto.response;

import lombok.*;

/**
 * packageName    : store.mybooks.front.payment.dto.response<br>
 * fileName       : TossPaymentResponse<br>
 * author         : minsu11<br>
 * date           : 3/18/24<br>
 * description    : 결제 후 toss에서 제공하는 타입
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/18/24        minsu11       최초 생성<br>
 */
@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
@ToString
public class TossPaymentResponse {
    String mid;
    String version;
    String paymentKey;
    String orderId;
    String orderName;
    String method;
    Integer totalAmount;
    String balanceAmount;
    String suppliedAmount;
    String status;
    String requestedAt;
    String approvedAt;
    String type;
}
