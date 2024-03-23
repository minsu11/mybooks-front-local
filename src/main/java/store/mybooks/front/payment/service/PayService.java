package store.mybooks.front.payment.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import store.mybooks.front.order.adaptor.OrderAdaptor;
import store.mybooks.front.order.dto.response.BookOrderDetailResponse;
import store.mybooks.front.payment.adaptor.PayAdaptor;
import store.mybooks.front.payment.dto.request.PayCancelOrderNumberRequest;
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
     * @param response response
     * @return the toss payment response
     */
    public TossPaymentResponse cancelToss(PaymentResponse response) {
        TossPaymentCancelRequest request = new TossPaymentCancelRequest("결제 취소");

        return payAdaptor.cancelPay(request, response);
    }

    /**
     * payment key 조회.
     *
     * @param request the request
     * @return the payment key
     */
    public PaymentResponse getPaymentKey(PayCancelOrderNumberRequest request) {
        return payAdaptor.getPaymentKey(request);
    }

    public void cancelPayAfterProcess(PayCancelRequest request) {
        payAdaptor.cancelPayAfterProcess(request);
    }

}
