package store.mybooks.front.jwt.adaptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.jwt.dto.request.TokenCreateRequest;
import store.mybooks.front.jwt.dto.response.TokenCreateResponse;

/**
 * packageName    : store.mybooks.front.jwt<br>
 * fileName       : tokenAdaptor<br>
 * author         : masiljangajji<br>
 * date           : 2/28/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/28/24        masiljangajji       최초 생성
 */
@Component
@RequiredArgsConstructor
public class TokenAdaptor {

    private final RestTemplate restTemplate;

    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    public TokenCreateResponse createToken(TokenCreateRequest tokenCreateRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<TokenCreateRequest> requestEntity = new HttpEntity<>(tokenCreateRequest, headers);

        // todo 지금 로컬설정임 나중에 배포하고 바꾸기
        ResponseEntity<TokenCreateResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/auth", HttpMethod.POST,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        });

        if (responseEntity.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException();
        }

        return responseEntity.getBody();
    }


}
