package store.mybooks.front.order.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.config.GatewayAdaptorProperties;
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
    private static final String URL = "/api/order";

    /**
     * methodName : checkOrderUserAddressInfo<br>
     * author : minsu11<br>
     * description : 회원이 주문에서 선택한 주소가 실제로 회원이 등록된 주소인지 확인.
     * <br> *
     *
     * @param id 주소 아이디
     */
    public void checkOrderUserAddressInfo(Long id) {
        ResponseEntity<Object> exchange = restTemplate.exchange(URL + "/check/address/{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Object>() {
                }, id);
        Utils.getResponseEntity(exchange, HttpStatus.NO_CONTENT);
    }

}
