package store.mybooks.front.cart.adaptor;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.cart.LoginCartDataMoveEvent;
import store.mybooks.front.cart.LogoutCartDataMoveEvent;
import store.mybooks.front.cart.domain.CartUserRedisKeyNameRequest;
import store.mybooks.front.cart.service.CartUserService;
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
    private final CartUserService cartUserService;
    private final GatewayAdaptorProperties gatewayAdaptorProperties;
    private static final String URL_CART = "/api/carts";


    @EventListener
    public void moveDataRedisToMysql(LoginCartDataMoveEvent loginCartDataMoveEvent) {
        CartUserRedisKeyNameRequest cartUserRedisKeyNameRequest =
                new CartUserRedisKeyNameRequest(cartUserService.cartKey());

        HttpHeaders headers = Utils.getHttpHeader();
        HttpEntity<CartUserRedisKeyNameRequest> requestEntity = new HttpEntity<>(cartUserRedisKeyNameRequest, headers);
        ResponseEntity<Void> exchange =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL_CART + "/user-login",
                        HttpMethod.POST, requestEntity, new ParameterizedTypeReference<Void>() {
                        });

        if (!Objects.equals(exchange.getStatusCode(), HttpStatus.CREATED)) {
            throw new RuntimeException();
        }
    }

    @EventListener
    public void moveDataMysqlToRedis(LogoutCartDataMoveEvent logoutCartDataMoveEvent) {
        CartUserRedisKeyNameRequest cartUserRedisKeyNameRequest =
                new CartUserRedisKeyNameRequest(cartUserService.cartKey());

        HttpHeaders headers = Utils.getHttpHeader();
        HttpEntity<CartUserRedisKeyNameRequest> requestEntity = new HttpEntity<>(cartUserRedisKeyNameRequest, headers);
        ResponseEntity<Void> exchange =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL_CART + "/user-logout",
                        HttpMethod.POST, requestEntity, new ParameterizedTypeReference<Void>() {
                        });

        if (!Objects.equals(exchange.getStatusCode(), HttpStatus.CREATED)) {
            throw new RuntimeException();
        }
    }


}
