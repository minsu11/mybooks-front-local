package store.mybooks.front.payment.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.mybooks.front.cart.service.CartUserService;
import store.mybooks.front.order.dto.response.BookOrderDetailResponse;
import store.mybooks.front.order.dto.response.BookOrderPayInfoResponse;
import store.mybooks.front.order.service.OrderInfoCheckService;
import store.mybooks.front.order.service.OrderService;
import store.mybooks.front.payment.adaptor.PayAdaptor;
import store.mybooks.front.payment.dto.request.PayCreateRequest;
import store.mybooks.front.payment.dto.request.TossPaymentRequest;
import store.mybooks.front.payment.dto.response.PayCreateResponse;
import store.mybooks.front.payment.dto.response.TossPaymentResponse;
import store.mybooks.front.payment.service.PayService;

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
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/pay")
public class PayRestController {
    private final PayService payService;
    private final OrderService orderService;
    private final PayAdaptor payAdaptor;
    private final CartUserService cartUserService;
    private final OrderInfoCheckService orderInfoCheckService;

    /**
     * 결제 .
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping("/confirm")
    public ResponseEntity<TossPaymentResponse> confirmPayment(@RequestBody TossPaymentRequest request) {
        // 재고
        List<BookOrderDetailResponse> bookOrderDetailResponses =
                orderService.getBookOrderDetail(request.getOrderId());
        orderInfoCheckService.isCheckAmountBookCart(bookOrderDetailResponses);
        return ResponseEntity.status(HttpStatus.OK).body(payService.createTossPayment(request));
    }

    @GetMapping("/info/{orderNumber}")
    public ResponseEntity<BookOrderPayInfoResponse> getBookOrderPayInfo(@PathVariable(name = "orderNumber") String orderNumber) {
        List<BookOrderDetailResponse> bookOrderDetailResponses =
                orderService.getBookOrderDetail(orderNumber);
        orderInfoCheckService.isCheckAmountBookCart(bookOrderDetailResponses);

        BookOrderPayInfoResponse orderPayInfoResponse = orderService.getBookOrderPayInfo(orderNumber);
        log.info("결제 정보 value: {}", orderPayInfoResponse);
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderPayInfoResponse);
    }

    /**
     * 결제가 성공 시 결제 테이블 생성.
     *
     * @param payCreateRequest the pay create request
     * @return the response entity
     */
    @PostMapping("/info/success")
    public ResponseEntity<PayCreateResponse> payProcessing(@RequestBody PayCreateRequest payCreateRequest) {
        PayCreateResponse response = payAdaptor.createResponse(payCreateRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    /**
     * 결제가 성공된 장바구니 삭제.
     *
     * @return the response entity
     */
    @GetMapping("/info/cart")
    public ResponseEntity<Void> removeUserCart() {
        log.debug("장바구니 삭제 호출 전");
        cartUserService.deleteAllBookFromCart();
        log.debug("장바구니 삭제 호출 후");
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }
}
