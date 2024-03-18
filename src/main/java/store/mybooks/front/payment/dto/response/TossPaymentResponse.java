package store.mybooks.front.payment.dto.response;

import lombok.Data;

/**
 * packageName    : store.mybooks.front.payment.dto.response<br>
 * fileName       : TossPaymentResponse<br>
 * author         : minsu11<br>
 * date           : 3/18/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/18/24        minsu11       최초 생성<br>
 */
@Data
public class TossPaymentResponse {

    String mid; // 가맹점 Id -> tosspayments
    String version; // Payment 객체 응답 버전
    String paymentKey;
    String orderId;
    String orderName;
    String method; // 결제 수단
    String totalAmount;
    String balanceAmount;
    String suppliedAmount;
    String vat; // 부가가치세
    String status; // 결제 처리 상태
    String requestedAt;
    String approvedAt;
    String type; // 결제 타입 정보 (NOMAL / BILLING / CONNECTPAY)

}
