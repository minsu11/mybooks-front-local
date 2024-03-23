package store.mybooks.front.payment.controller;

import java.util.List;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.mybooks.front.order.dto.response.BookOrderDetailResponse;
import store.mybooks.front.order.dto.response.BookOrderPayInfoResponse;
import store.mybooks.front.order.service.OrderInfoCheckService;
import store.mybooks.front.order.service.OrderService;
import store.mybooks.front.payment.adaptor.PayAdaptor;
import store.mybooks.front.payment.dto.request.PayCancelOrderNumberRequest;
import store.mybooks.front.payment.dto.request.PayCancelRequest;
import store.mybooks.front.payment.dto.request.PayCreateRequest;
import store.mybooks.front.payment.dto.request.TossPaymentRequest;
import store.mybooks.front.payment.dto.response.PayCreateResponse;
import store.mybooks.front.payment.dto.response.PaymentResponse;
import store.mybooks.front.payment.dto.response.TossPaymentResponse;
import store.mybooks.front.payment.service.PayService;
import store.mybooks.front.utils.CookieUtils;

/**
 * packageName    : store.mybooks.front.payment.controller<br>
 * fileName       : PayRestController<br>
 * author         : minsu11<br>
 * date           : 3/18/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/18/24        minsu11       최초 생성<br>
 */
@RestController
@RequiredArgsConstructor
public class PayRestController {
    private final PayService payService;
    private final OrderService orderService;
    private final PayAdaptor payAdaptor;
    private final OrderInfoCheckService orderInfoCheckService;
    private static final String USER_COOKIE_VALUE = "identity_cookie";

    /**
     * 결제 .
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping("/pay/confirm")
    public ResponseEntity<TossPaymentResponse> confirmPayment(@RequestBody TossPaymentRequest request) {
        // 재고ㅇ
        List<BookOrderDetailResponse> bookOrderDetailResponses =
                orderService.getBookOrderDetail(request.getOrderId());
        orderInfoCheckService.isCheckAmountBookCart(bookOrderDetailResponses);
        return ResponseEntity.status(HttpStatus.OK).body(payService.createTossPayment(request));
    }

    @GetMapping("/pay/info/{orderNumber}")
    public ResponseEntity<BookOrderPayInfoResponse> getBookOrderPayInfo(@PathVariable(name = "orderNumber") String orderNumber) {
        List<BookOrderDetailResponse> bookOrderDetailResponses =
                orderService.getBookOrderDetail(orderNumber);
        orderInfoCheckService.isCheckAmountBookCart(bookOrderDetailResponses);

        BookOrderPayInfoResponse orderPayInfoResponse = orderService.getBookOrderPayInfo(orderNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderPayInfoResponse);
    }

    /**
     * 결제가 성공 시 결제 테이블 생성.
     *
     * @param payCreateRequest the pay create request
     * @return the response entity
     */
    @PostMapping("/cart/info/success")
    public ResponseEntity<PayCreateResponse> payProcessing(@RequestBody PayCreateRequest payCreateRequest,
                                                           @CookieValue(name = USER_COOKIE_VALUE, required = false) Cookie cookie,
                                                           HttpServletRequest request) {
        PayCreateResponse response;
        if (Objects.nonNull(CookieUtils.getIdentityCookieValue(request))) {
            response = payAdaptor.createResponse(payCreateRequest);
        } else {

            response = payAdaptor.createNonUserOrderResponse(payCreateRequest);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping("/pay/info/cancel")
    public ResponseEntity<TossPaymentResponse> cancelProcessing(@RequestBody PayCancelOrderNumberRequest cancelRequest) {
        PaymentResponse response = payService.getPaymentKey(cancelRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(payService.cancelToss(response));
    }

    @PostMapping("/pay/info/cancel/process")
    public ResponseEntity<Void> cancelPayAfterProcess(@RequestBody PayCancelRequest request) {
        payService.cancelPayAfterProcess(request);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

}
