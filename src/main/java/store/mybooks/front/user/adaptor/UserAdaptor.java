package store.mybooks.front.user.adaptor;

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
import store.mybooks.front.user.dto.request.UserCreateRequest;
import store.mybooks.front.user.dto.request.UserGradeModifyRequest;
import store.mybooks.front.user.dto.request.UserLoginRequest;
import store.mybooks.front.user.dto.request.UserModifyRequest;
import store.mybooks.front.user.dto.request.UserPasswordModifyRequest;
import store.mybooks.front.user.dto.request.UserStatusModifyRequest;
import store.mybooks.front.user.dto.response.PhoneNumberAuthResponse;
import store.mybooks.front.user.dto.response.UserCreateResponse;
import store.mybooks.front.user.dto.response.UserGetResponse;
import store.mybooks.front.user.dto.response.UserGradeModifyResponse;
import store.mybooks.front.user.dto.response.UserLoginResponse;
import store.mybooks.front.user.dto.response.UserModifyResponse;
import store.mybooks.front.user.dto.response.UserPasswordModifyResponse;
import store.mybooks.front.user.dto.response.UserStatusModifyResponse;

/**
 * packageName    : store.mybooks.front.user.adaptor
 * fileName       : UserAdaptor
 * author         : masiljangajji
 * date           : 2/23/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/23/24        masiljangajji       최초 생성
 */

@Component
@RequiredArgsConstructor
public class UserAdaptor {

    private final RestTemplate restTemplate;

    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    public void loginUser(UserLoginRequest userLoginRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<UserLoginRequest> requestEntity = new HttpEntity<>(userLoginRequest, headers);

        ResponseEntity<UserLoginResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/users/login", HttpMethod.POST,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        });

        System.out.println(requestEntity.getBody().getPassword());

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }

    public void createUser(UserCreateRequest createRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<UserCreateRequest> requestEntity = new HttpEntity<>(createRequest, headers);

        ResponseEntity<UserCreateResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/users", HttpMethod.POST,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        });


        if (responseEntity.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException();
        }
    }

    public PhoneNumberAuthResponse getPhoneNumberAuthResponse(){

        ResponseEntity<PhoneNumberAuthResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/auth/phone", HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        });


        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return responseEntity.getBody();
    }
    public UserGetResponse findUserById(Long userId) {

        ResponseEntity<UserGetResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/users/{userId}", HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        },userId);


        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return responseEntity.getBody();

    }

    public void modifyUserPassword(Long userId, UserPasswordModifyRequest modifyRequest){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<UserPasswordModifyRequest> requestEntity = new HttpEntity<>(modifyRequest, headers);

        ResponseEntity<UserPasswordModifyResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/users/{userId}/password", HttpMethod.PUT,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        },userId);


        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }

    public void modifyUserStatus(Long userId, UserStatusModifyRequest modifyRequest){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<UserStatusModifyRequest> requestEntity = new HttpEntity<>(modifyRequest, headers);

        ResponseEntity<UserStatusModifyResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/users/{userId}/status", HttpMethod.PUT,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        },userId);


        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }

    public void modifyUserGrade(Long userId, UserGradeModifyRequest modifyRequest){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<UserGradeModifyRequest> requestEntity = new HttpEntity<>(modifyRequest, headers);

        ResponseEntity<UserGradeModifyResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/users/{userId}/grade", HttpMethod.PUT,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        },userId);


        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }

    public void modifyUser(Long userId, UserModifyRequest modifyRequest){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<UserModifyRequest> requestEntity = new HttpEntity<>(modifyRequest, headers);

        ResponseEntity<UserModifyResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/users/{userId}", HttpMethod.PUT,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        },userId);


        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }

    public void deleteUser(Long userId){

        ResponseEntity<UserModifyResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/users/{userId}", HttpMethod.DELETE,
                        null,
                        new ParameterizedTypeReference<>() {
                        },userId);


        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }

}
