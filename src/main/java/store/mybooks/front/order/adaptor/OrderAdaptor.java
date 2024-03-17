package store.mybooks.front.order.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.auth.Annotation.RequiredAuthorization;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.order.dto.request.BookOrderCreateRequest;
import store.mybooks.front.order.dto.response.BookOrderCreateResponse;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.order.adaptor<br>
 * fileName       : OrderAdaptor<br>
 * author         : minsu11<br>
 * date           : 3/16/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/16/24        minsu11       최초 생성<br>
 */
@Component
@RequiredArgsConstructor
public class OrderAdaptor {
    private final RestTemplate restTemplate;
    private final GatewayAdaptorProperties gatewayAdaptorProperties;
    private static final String URL = "/api/orders";
    private static final String MEMBER_URL = "/api/member/orders";

    /**
     * methodName : checkOrderUserAddressInfo<br>
     * author : minsu11<br>
     * description : 회원이 주문에서 선택한 주소가 실제로 회원이 등록된 주소인지 확인.
     * <br> *
     *
     * @param id 주소 아이디
     */
    public void checkOrderUserAddressInfo(Long id) {
        ResponseEntity<Object> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/check/address/{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Object>() {
                }, id);
        Utils.getResponseEntity(exchange, HttpStatus.NO_CONTENT);
    }


    @RequiredAuthorization
    public BookOrderCreateResponse createBookOrder(BookOrderCreateRequest request) {
        HttpEntity<BookOrderCreateRequest> httpEntity = new HttpEntity<>(request, Utils.getAuthHeader());
        ResponseEntity<BookOrderCreateResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + MEMBER_URL,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<BookOrderCreateResponse>() {
                });
        return Utils.getResponseEntity(exchange, HttpStatus.CREATED);
    }
}
