package store.mybooks.front.payment.adaptor;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.config.TossAppKey;
import store.mybooks.front.payment.dto.request.TossPaymentRequest;
import store.mybooks.front.payment.dto.response.TossPaymentResponse;
import store.mybooks.front.payment.exception.PayFailedException;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.payment.adaptor<br>
 * fileName       : PayAdaptor<br>
 * author         : minsu11<br>
 * date           : 3/18/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/18/24        minsu11       최초 생성<br>
 */
@Component
@RequiredArgsConstructor
public class PayAdaptor {
    private final TossAppKey tossAppKey;
    private final RestTemplate restTemplate;
    private final static String TOSS_URL = "https://api.tosspayments.com/v1/payments/confirm";

    public TossPaymentResponse confirmPayment(TossPaymentRequest request) {
        HttpHeaders headers = new HttpHeaders();
        String apiKey = tossAppKey.getKey();

        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((apiKey + ":").getBytes(StandardCharsets.UTF_8));
        String authorizations = new String(encodedBytes, 0, encodedBytes.length);

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBasicAuth(authorizations);
        try {
            ResponseEntity<TossPaymentResponse> exchange = restTemplate.exchange(
                    TOSS_URL,
                    HttpMethod.POST,
                    new HttpEntity<>(request, headers),
                    new ParameterizedTypeReference<TossPaymentResponse>() {
                    }
            );
            return Utils.getResponseEntity(exchange, HttpStatus.OK);
        } catch (Exception e) {
            throw new PayFailedException();
        }
    }

}
