package store.mybooks.front.user_status.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.user_status.dto.response.UserStatusGetResponse;

/**
 * packageName    : store.mybooks.front.user_status.adaptor<br>
 * fileName       : UserStatusAdaptor<br>
 * author         : masiljangajji<br>
 * date           : 2/22/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/22/24        masiljangajji       최초 생성
 */
@Component
@RequiredArgsConstructor
public class UserStatusAdaptor {

    private final RestTemplate restTemplate;

    private final GatewayAdaptorProperties gatewayAdaptorProperties;


    public UserStatusGetResponse findUserStatusById(String id) {
        ResponseEntity<UserStatusGetResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/users-statuses/{statusId}", HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        },id);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return responseEntity.getBody();
    }


}
