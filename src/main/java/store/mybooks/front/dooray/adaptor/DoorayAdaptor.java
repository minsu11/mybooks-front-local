package store.mybooks.front.dooray.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.dooray.dto.DoorayAuthResponse;

/**
 * packageName    : store.mybooks.front.dooray.dto.adaptor<br>
 * fileName       : DoorayAdaptor<br>
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
public class DoorayAdaptor {

    private final RestTemplate restTemplate;

    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    /**
     * methodName : getDoorayAuthNumber
     * author : masiljangajji
     * description : 유저 회원가입 , 전화번호 변경에 필요한 인증메시지를 요청함
     *
     * @return phone number auth response
     */
    public DoorayAuthResponse getDoorayAuthNumber(){

        ResponseEntity<DoorayAuthResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/dooray", HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        });
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return responseEntity.getBody();
    }

}
