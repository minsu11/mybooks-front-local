package store.mybooks.front.admin.user_grade.adaptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.user_grade.dto.request.UserGradeCreateRequest;
import store.mybooks.front.admin.user_grade.dto.response.UserGradeGetResponse;
import store.mybooks.front.auth.Annotation.RequiredAuthorization;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.admin.member_grade.adaptor<br>
 * fileName       : MemberGradeAdaptor<br>
 * author         : masiljangajji<br>
 * date           : 3/22/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/22/24        masiljangajji       최초 생성
 */
@Component
@RequiredArgsConstructor
public class UserGradeAdaptor {

    private final RestTemplate restTemplate;

    private static final String URL_ADMIN = "/api/admin/users-grades";

    private final GatewayAdaptorProperties gatewayAdaptorProperties;


    /**
     * methodName : findUserStatusById
     * author : masiljangajji
     * description : 유저가 가질 수 있는 상태를 찾음
     *
     * @return user status get response
     */

    @RequiredAuthorization
    public List<UserGradeGetResponse> findAllAvailableUserGrade() {
        ResponseEntity<List<UserGradeGetResponse>> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL_ADMIN, HttpMethod.GET,
                        new HttpEntity<>(Utils.getAuthHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return responseEntity.getBody();
    }

    @RequiredAuthorization
    public List<UserGradeGetResponse> findAllUserGrade() {

        ResponseEntity<List<UserGradeGetResponse>> responseEntity =
                restTemplate.exchange(
                        gatewayAdaptorProperties.getAddress() + URL_ADMIN + "/all", HttpMethod.GET,
                        new HttpEntity<>(Utils.getAuthHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return responseEntity.getBody();
    }

    @RequiredAuthorization
    public void createUserGrade(UserGradeCreateRequest createRequest) {
        ResponseEntity<Void> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL_ADMIN, HttpMethod.POST,
                        new HttpEntity<>(createRequest, Utils.getAuthHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        if (responseEntity.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException();
        }

    }


}
