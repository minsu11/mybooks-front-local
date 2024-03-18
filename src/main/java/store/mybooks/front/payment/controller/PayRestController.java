package store.mybooks.front.payment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.mybooks.front.payment.dto.request.TossPaymentRequest;
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

    @PostMapping("/confirm")
    public ResponseEntity<TossPaymentResponse> confirmPayment(@RequestBody TossPaymentRequest request) throws Exception {
        TossPaymentResponse tossPaymentResponse = payService.getTossPayment(request);
        log.info("toss value: {}", tossPaymentResponse);

        return ResponseEntity.status(HttpStatus.OK).body(tossPaymentResponse);
    }


}
