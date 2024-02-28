package store.mybooks.front.user.adaptor;

import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.filter.OncePerRequestFilter;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.dooray.dto.DoorayAuthResponse;
import store.mybooks.front.jwt.dto.request.TokenCreateRequest;
import store.mybooks.front.jwt.dto.response.TokenCreateResponse;
import store.mybooks.front.user.dto.request.UserCreateRequest;
import store.mybooks.front.user.dto.request.UserGradeModifyRequest;
import store.mybooks.front.user.dto.request.UserLoginRequest;
import store.mybooks.front.user.dto.request.UserModifyRequest;
import store.mybooks.front.user.dto.request.UserPasswordModifyRequest;
import store.mybooks.front.user.dto.request.UserStatusModifyRequest;
import store.mybooks.front.user.dto.response.UserCreateResponse;
import store.mybooks.front.user.dto.response.UserGetResponse;
import store.mybooks.front.user.dto.response.UserGradeModifyResponse;
import store.mybooks.front.user.dto.response.UserLoginResponse;
import store.mybooks.front.user.dto.response.UserModifyResponse;
import store.mybooks.front.user.dto.response.UserPasswordModifyResponse;
import store.mybooks.front.user.dto.response.UserStatusModifyResponse;

/**
 * packageName    : store.mybooks.front.user.adaptor<br>
 * fileName       : UserAdaptor<br>
 * author         : masiljangajji<br>
 * date           : 2/23/24<br>
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


    /**
     * methodName : loginUser
     * author : masiljangajji
     * description : 유저의 로그인 요청을 처리함
     *
     * @param userLoginRequest login request
     */
    public UserLoginResponse loginUser(UserLoginRequest userLoginRequest) {



        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<UserLoginRequest> requestEntity = new HttpEntity<>(userLoginRequest, headers);

        ResponseEntity<UserLoginResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/users/login", HttpMethod.POST,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        });

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return responseEntity.getBody();
    }


    /**
     * methodName : createUser
     * author : masiljangajji
     * description : 유저의 회원가입 요청을 처리함
     *
     * @param createRequest request
     */
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


    /**
     * methodName : findUserById
     * author : masiljangajji
     * description : 유저의 Id로 유저의 정보를 가져옴
     *
     * @param userId id
     * @return user get response
     */
    public UserGetResponse findUserById(Long userId,HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) { // 쿠키 이름이 "token"인 경우
                    token = cookie.getValue(); // 쿠키의 값 가져오기
                    break;
                }
            }
        }

        System.out.println(token);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.set("token", token);


        HttpEntity<Void> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<UserGetResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/users/{userId}", HttpMethod.GET,
                        httpEntity,
                        new ParameterizedTypeReference<>() {
                        }, userId);


        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return responseEntity.getBody();
    }


    /**
     * methodName : modifyUserPassword
     * author : masiljangajji
     * description : 유저의 비밀번호를 변경함
     *
     * @param userId        id
     * @param modifyRequest request
     */
    public void modifyUserPassword(Long userId, UserPasswordModifyRequest modifyRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<UserPasswordModifyRequest> requestEntity = new HttpEntity<>(modifyRequest, headers);

        ResponseEntity<UserPasswordModifyResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/users/{userId}/password",
                        HttpMethod.PUT,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        }, userId);


        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }


    /**
     * methodName : modifyUserStatus
     * author : masiljangajji
     * description : 유저의 상태를 변경함
     *
     * @param userId        id
     * @param modifyRequest request
     */
    public void modifyUserStatus(Long userId, UserStatusModifyRequest modifyRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<UserStatusModifyRequest> requestEntity = new HttpEntity<>(modifyRequest, headers);

        ResponseEntity<UserStatusModifyResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/users/{userId}/status",
                        HttpMethod.PUT,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        }, userId);


        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }


    /**
     * methodName : modifyUserGrade
     * author : masiljangajji
     * description : 유저의 등급을 변경함
     *
     * @param userId        id
     * @param modifyRequest request
     */
    public void modifyUserGrade(Long userId, UserGradeModifyRequest modifyRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<UserGradeModifyRequest> requestEntity = new HttpEntity<>(modifyRequest, headers);

        ResponseEntity<UserGradeModifyResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/users/{userId}/grade",
                        HttpMethod.PUT,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        }, userId);


        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }


    /**
     * methodName : modifyUser
     * author : masiljangajji
     * description : 유저의 정보를 변경함 (이름,전화번호)
     *
     * @param userId        id
     * @param modifyRequest request
     */
    public void modifyUser(Long userId, UserModifyRequest modifyRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<UserModifyRequest> requestEntity = new HttpEntity<>(modifyRequest, headers);

        ResponseEntity<UserModifyResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/users/{userId}", HttpMethod.PUT,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        }, userId);


        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }


    /**
     * methodName : deleteUser
     * author : masiljangajji
     * description : 회원탈퇴
     *
     * @param userId id
     */
    public void deleteUser(Long userId) {

        ResponseEntity<UserModifyResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/users/{userId}", HttpMethod.DELETE,
                        null,
                        new ParameterizedTypeReference<>() {
                        }, userId);


        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }

}
