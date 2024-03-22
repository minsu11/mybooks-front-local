package store.mybooks.front.order.adaptor;

import java.util.List;
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
import store.mybooks.front.order.dto.response.BookOrderDetailResponse;
import store.mybooks.front.order.dto.response.BookOrderInfoResponse;
import store.mybooks.front.order.dto.response.BookOrderPayInfoResponse;
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
    private static final String ORDER_DETAIL_URL = "/api/order-details";

    /**
     * methodName : checkOrderUserAddressInfo<br>
     * author : minsu11<br>
     * description : 회원이 주문에서 선택한 주소가 실제로 회원이 등록된 주소인지 확인.
     * <br> *
     *
     * @param id 주소 아이디
     */
    public Boolean checkOrderUserAddressInfo(Long id) {
        ResponseEntity<Boolean> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/check/address/{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Boolean>() {
                }, id);
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    /**
     * Create book order book order create response.
     *
     * @param request the request
     * @return the book order create response
     */
    @RequiredAuthorization
    public BookOrderCreateResponse createUserBookOrder(BookOrderCreateRequest request) {
        HttpEntity<BookOrderCreateRequest> httpEntity = new HttpEntity<>(request, Utils.getAuthHeader());
        ResponseEntity<BookOrderCreateResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + MEMBER_URL,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<BookOrderCreateResponse>() {
                });
        return Utils.getResponseEntity(exchange, HttpStatus.CREATED);
    }

    /**
     * 비회원 주문 결제.
     *
     * @param request the request
     * @return the book order create response
     */
    public BookOrderCreateResponse createNonUserBookOrder(BookOrderCreateRequest request) {
        HttpEntity<BookOrderCreateRequest> httpEntity = new HttpEntity<>(request, Utils.getHttpHeader());
        ResponseEntity<BookOrderCreateResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/non/user",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<BookOrderCreateResponse>() {
                });
        return Utils.getResponseEntity(exchange, HttpStatus.CREATED);
    }

    /**
     * 주문 번호 중복 확인.
     *
     * @param orderNumber the order number
     * @return the boolean
     */
    public Boolean checkBookOrderNumber(String orderNumber) {
        ResponseEntity<Boolean> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/orderNumber/{orderNumber}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Boolean>() {
                }, orderNumber);
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    public BookOrderInfoResponse getPayBookOrderInfo(String orderNumber) {
        ResponseEntity<BookOrderInfoResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/info/pay/{orderNumber}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<BookOrderInfoResponse>() {
                }, orderNumber
        );

        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    /**
     * 주문 결제 정보 조회.
     *
     * @param orderNumber the order number
     * @return the book order pay info
     */
    public BookOrderPayInfoResponse getBookOrderPayInfo(String orderNumber) {
        ResponseEntity<BookOrderPayInfoResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/info/{orderNumber}/pay",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<BookOrderPayInfoResponse>() {
                }, orderNumber
        );
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    /**
     * 주문번호로 조회된 주문 상세 목록.
     *
     * @param orderNumber the order number
     * @return the book order detail list
     */
    public List<BookOrderDetailResponse> getBookOrderDetailList(String orderNumber) {
        ResponseEntity<List<BookOrderDetailResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + ORDER_DETAIL_URL + "/{orderNumber}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BookOrderDetailResponse>>() {
                }, orderNumber
        );
        return Utils.getResponseEntity(exchange, HttpStatus.OK);

    }


}
