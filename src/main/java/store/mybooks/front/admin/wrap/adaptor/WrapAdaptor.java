package store.mybooks.front.admin.wrap.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.wrap.dto.response.WrapResponse;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.admin.wrap<br>
 * fileName       : WrapAdaptor<br>
 * author         : minsu11<br>
 * date           : 2/28/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/28/24        minsu11       최초 생성<br>
 */
@Component
@RequiredArgsConstructor
public class WrapAdaptor {
    private final GatewayAdaptorProperties gatewayAdaptorProperties;
    private final RestTemplate restTemplate;

    /**
     * methodName : getWrapResponse<br>
     * author : minsu11<br>
     * description : {@code id}에 따른 포장지 조회
     * <br> *
     *
     * @param id 조회할 포장지 {@code id}
     * @return wrap response
     */
    public WrapResponse getWrapResponse(Integer id) {
        String url = gatewayAdaptorProperties.getAddress() + "/api/wraps/{id}";
        ResponseEntity<WrapResponse> exchange = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<WrapResponse>() {
                }, id);
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }
}
