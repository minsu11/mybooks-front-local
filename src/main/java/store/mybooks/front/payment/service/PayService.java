package store.mybooks.front.payment.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import store.mybooks.front.order.adaptor.OrderAdaptor;
import store.mybooks.front.order.dto.response.BookOrderDetailResponse;
import store.mybooks.front.payment.adaptor.PayAdaptor;
import store.mybooks.front.payment.dto.request.PayCancelReasonRequest;
import store.mybooks.front.payment.dto.request.PayCancelRequest;
import store.mybooks.front.payment.dto.request.TossPaymentCancelRequest;
import store.mybooks.front.payment.dto.request.TossPaymentRequest;
import store.mybooks.front.payment.dto.response.PaymentResponse;
import store.mybooks.front.payment.dto.response.TossPaymentResponse;

/**
 * packageName    : store.mybooks.front.payment.service<br>
 * fileName       : PayService<br>
 * author         : minsu11<br>
 * date           : 3/18/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/18/24        minsu11       최초 생성<br>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PayService {
    private final PayAdaptor payAdaptor;
    private final OrderAdaptor orderAdaptor;

    /**
     * 주문 상세에서 쿠폰을 사용했는 지 판별.
     *
     * @param orderInfoResponseList the order info response list
     * @return the boolean
     */
    public boolean checkCouponUsed(List<BookOrderDetailResponse> orderInfoResponseList) {
        for (BookOrderDetailResponse orderInfo : orderInfoResponseList) {
            boolean isCheck = orderInfo.getIsCouponUsed();
            if (isCheck) {
                return true;
            }
        }
        return false;
    }

    public TossPaymentResponse createTossPayment(TossPaymentRequest request) {
        return payAdaptor.confirmPayment(request);
    }

    /**
     * 결제 취소.
     *
     * @param orderNumber 주문 번호
     * @param payRequest  주문 취소 사유
     * @return the toss payment response
     */
    public TossPaymentResponse cancelToss(String orderNumber, PayCancelReasonRequest payRequest) {
        PaymentResponse response = payAdaptor.getPaymentKey(orderNumber);
        log.debug("payment key: {}", response.getPaymentKey());
        TossPaymentCancelRequest request = new TossPaymentCancelRequest(payRequest.getCancelReason());
        return payAdaptor.cancelPay(request, response);
    }


    /**
     * 토스에게 결제 취소 상태 받은 후 처리.
     *
     * @param response the response
     */
    public void cancelPayAfterProcess(TossPaymentResponse response) {
        PayCancelRequest request = new PayCancelRequest(response.getPaymentKey(), response.getOrderId(),
                response.getStatus(), response.getTotalAmount(), response.getRequestedAt());
        payAdaptor.cancelPayAfterProcess(request);
    }

}
