package store.mybooks.front.userpoint.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.userpoint.dto.response.PointResponse;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.userpoint.adaptor<br>
 * fileName       : UserPointAdaptor<br>
 * author         : minsu11<br>
 * date           : 3/10/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/10/24        minsu11       최초 생성<br>
 */
@Component
@RequiredArgsConstructor
public class UserPointAdaptor {
    private final RestTemplate restTemplate;
    private final GatewayAdaptorProperties gatewayAdaptorProperties;
    private static final String URL = "/api/point-histories";

    public PointResponse getPointsHeld() {
        // 인증 어노테이션 달기
        HttpHeaders headers = Utils.getAuthHeader();
        HttpEntity<Object> httpEntity = new HttpEntity(headers);
        ResponseEntity<PointResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/point",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<PointResponse>() {
                }
        );
        return Utils.getResponseEntity(exchange, HttpStatus.OK);

    }
}
