package store.mybooks.front.payment.adaptor;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import store.mybooks.front.auth.Annotation.RequiredAuthorization;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.config.TossAppKey;
import store.mybooks.front.payment.dto.request.PayCancelRequest;
import store.mybooks.front.payment.dto.request.PayCreateRequest;
import store.mybooks.front.payment.dto.request.TossPaymentCancelRequest;
import store.mybooks.front.payment.dto.request.TossPaymentRequest;
import store.mybooks.front.payment.dto.response.PayCreateResponse;
import store.mybooks.front.payment.dto.response.PaymentResponse;
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
    private static final String TOSS_URL = "https://api.tosspayments.com/v1/payments";
    private final GatewayAdaptorProperties gatewayAdaptorProperties;
    private static final String URL = "/api/pays";
    private static final String MEMBER_URL = "/api/member/pays";

    public TossPaymentResponse confirmPayment(TossPaymentRequest request) {
        HttpHeaders headers = Utils.getHttpHeader();
        String apiKey = tossAppKey.getKey();

        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((apiKey + ":").getBytes(StandardCharsets.UTF_8));
        String authorizations = new String(encodedBytes, 0, encodedBytes.length);

        headers.setBasicAuth(authorizations);
        try {
            ResponseEntity<TossPaymentResponse> exchange = restTemplate.exchange(
                    TOSS_URL + "/confirm",
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


    public TossPaymentResponse cancelPay(TossPaymentCancelRequest request, PaymentResponse response) {
        HttpHeaders headers = new HttpHeaders();

        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((tossAppKey.getKey() + ":").getBytes(StandardCharsets.UTF_8));
        String authorizations = new String(encodedBytes, 0, encodedBytes.length);

        headers.setBasicAuth(authorizations);
        String uri = UriComponentsBuilder
                .fromUriString(TOSS_URL)
                .path("/" + response.getPaymentKey() + "/cancel")
                .build().toString();

        try {
            ResponseEntity<TossPaymentResponse> exchange = restTemplate.exchange(
                    uri,
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

    /**
     * methodName : createPayment<br>
     * author : minsu11<br>
     * description : 결제 정보 저장.
     * <br>.
     *
     * @param request the request
     * @return the pay create response
     */
    @RequiredAuthorization
    public PayCreateResponse createResponse(PayCreateRequest request) {
        HttpEntity<PayCreateRequest> httpEntity = new HttpEntity<>(request, Utils.getAuthHeader());
        ResponseEntity<PayCreateResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + MEMBER_URL,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<PayCreateResponse>() {
                }
        );
        return Utils.getResponseEntity(exchange, HttpStatus.CREATED);
    }

    public PayCreateResponse createNonUserOrderResponse(PayCreateRequest request) {
        HttpEntity<PayCreateRequest> httpEntity = new HttpEntity<>(request, Utils.getHttpHeader());
        ResponseEntity<PayCreateResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/non/user",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<PayCreateResponse>() {
                }
        );
        return Utils.getResponseEntity(exchange, HttpStatus.CREATED);
    }

    public PaymentResponse getPaymentKey(PayCancelRequest request) {
        HttpEntity<PayCancelRequest> httpEntity = new HttpEntity<>(request, Utils.getHttpHeader());
        ResponseEntity<PaymentResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/{orderNumber}",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<PaymentResponse>() {
                }, request.getOrderNumber());

        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }
}
