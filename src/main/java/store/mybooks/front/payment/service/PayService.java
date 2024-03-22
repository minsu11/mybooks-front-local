package store.mybooks.front.payment.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import store.mybooks.front.order.dto.response.BookOrderDetailResponse;
import store.mybooks.front.payment.adaptor.PayAdaptor;
import store.mybooks.front.payment.dto.request.TossPaymentRequest;
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


}
