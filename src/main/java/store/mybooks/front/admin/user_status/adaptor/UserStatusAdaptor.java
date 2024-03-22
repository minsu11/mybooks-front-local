package store.mybooks.front.admin.user_status.adaptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.user_status.dto.response.UserStatusGetResponse;
import store.mybooks.front.auth.Annotation.RequiredAuthorization;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.utils.Utils;

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


    private static final String ADMIN_URL = "/api/admin/users-statuses";

    private final GatewayAdaptorProperties gatewayAdaptorProperties;


    /**
     * methodName : findUserStatusById
     * author : masiljangajji
     * description : 유저가 가질 수 있는 상태를 찾음
     *
     * @return user status get response
     */

    @RequiredAuthorization
    public List<UserStatusGetResponse> findAllUserStatus() {

        ResponseEntity<List<UserStatusGetResponse>> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + ADMIN_URL, HttpMethod.GET,
                        new HttpEntity<>(Utils.getAuthHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return responseEntity.getBody();
    }


}
