package store.mybooks.front.user_address.adaptor;

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
import store.mybooks.front.user_address.request.UserAddressCreateRequest;
import store.mybooks.front.user_address.request.UserAddressModifyRequest;
import store.mybooks.front.user_address.response.UserAddressCreateResponse;
import store.mybooks.front.user_address.response.UserAddressDeleteResponse;
import store.mybooks.front.user_address.response.UserAddressGetResponse;
import store.mybooks.front.user_address.response.UserAddressModifyResponse;

/**
 * packageName    : store.mybooks.front.user.user_address.adaptor<br>
 * fileName       : UserAddressAdaptor<br>
 * author         : masiljangajji<br>
 * date           : 2/25/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/25/24        masiljangajji       최초 생성
 */
@Component
@RequiredArgsConstructor
public class UserAddressAdaptor {

    private final RestTemplate restTemplate;

    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    /**
     * methodName : findAllAddressByUserId
     * author : masiljangajji
     * description : 유저의 모든 주소를 찾음
     *
     * @param userId id
     * @return list
     */
    public List<UserAddressGetResponse> findAllAddressByUserId(Long userId) {

        ResponseEntity<List<UserAddressGetResponse>> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/users/{userId}/addresses",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        }, userId);


        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
        return responseEntity.getBody();
    }

    /**
     * methodName : deleteUserAddress
     * author : masiljangajji
     * description : 유저의 주소중 하나를 찾음
     *
     * @param userId    id
     * @param addressId id
     */
    public void deleteUserAddress(Long userId, Long addressId) {

        ResponseEntity<UserAddressDeleteResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/users/{userId}/addresses/{addressId}",
                        HttpMethod.DELETE,
                        null,
                        new ParameterizedTypeReference<>() {
                        }, userId, addressId);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

    }

    /**
     * methodName : createUserAddress
     * author : masiljangajji
     * description : 유저 주소를 생성
     *
     * @param userId id
     * @param userAddressCreateRequest address create request
     */
    public void createUserAddress(Long userId, UserAddressCreateRequest userAddressCreateRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<UserAddressCreateRequest> requestEntity = new HttpEntity<>(userAddressCreateRequest, headers);

        ResponseEntity<UserAddressCreateResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/users/{userId}/addresses",
                        HttpMethod.POST,
                        requestEntity,
                        UserAddressCreateResponse.class,
                        userId);

        if (responseEntity.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException();
        }

    }

    /**
     * methodName : modifyUserAddress
     * author : masiljangajji
     * description : 유저의 주소정보를 변경 (별명,상세주소)
     *
     * @param userId    id
     * @param addressId id
     * @param userAddressModifyRequest    address modify request
     */
    public void modifyUserAddress(Long userId, Long addressId, UserAddressModifyRequest userAddressModifyRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<UserAddressModifyRequest> requestEntity = new HttpEntity<>(userAddressModifyRequest, headers);

        ResponseEntity<UserAddressModifyResponse> responseEntity =
                restTemplate.exchange(
                        gatewayAdaptorProperties.getAddress() + "/api/users/{userId}/addresses/{addressId}",
                        HttpMethod.PUT,
                        requestEntity,
                        UserAddressModifyResponse.class,
                        userId, addressId);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }


    }


}
