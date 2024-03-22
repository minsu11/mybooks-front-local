package store.mybooks.front.cart.adaptor;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.auth.Annotation.RequiredAuthorization;
import store.mybooks.front.cart.domain.CartDetail;
import store.mybooks.front.cart.domain.CartUserRedisKeyNameRequest;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.cart.adaptor <br/>
 * fileName       : CartAdaptor<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/8/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/8/24        Fiat_lux       최초 생성<br/>
 */
@Component
@RequiredArgsConstructor
public class CartAdaptor {
    private final RestTemplate restTemplate;
    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    private static final String URL_USER_CART = "/api/member/carts";


    @RequiredAuthorization
    public List<CartDetail> moveDataMysqlToRedis(String cartKey) {
        CartUserRedisKeyNameRequest cartUserRedisKeyNameRequest = new CartUserRedisKeyNameRequest(cartKey);
        HttpHeaders headers = Utils.getAuthHeader();
        HttpEntity<CartUserRedisKeyNameRequest> requestEntity = new HttpEntity<>(cartUserRedisKeyNameRequest, headers);
        ResponseEntity<List<CartDetail>> exchange =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL_USER_CART + "/get/items",
                        HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<CartDetail>>() {
                        });

        if (!Objects.equals(exchange.getStatusCode(), HttpStatus.CREATED)) {
            throw new RuntimeException();
        }
        return exchange.getBody();
    }


}
