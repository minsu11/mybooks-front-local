package store.mybooks.front.user_address.adaptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.auth.Annotation.RequiredAuthorization;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.user_address.request.UserAddressCreateRequest;
import store.mybooks.front.user_address.request.UserAddressModifyRequest;
import store.mybooks.front.user_address.response.UserAddressCreateResponse;
import store.mybooks.front.user_address.response.UserAddressDeleteResponse;
import store.mybooks.front.user_address.response.UserAddressGetResponse;
import store.mybooks.front.user_address.response.UserAddressModifyResponse;
import store.mybooks.front.utils.Utils;

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

    private static final String URL_MEMBER = "/api/member/users/addresses";
    private static final String URL_MEMBER_ID = "/api/member/users/addresses/{addressId}";
    private static final String URL_ADMIN_ID = "/api/admin/users/addresses";


    /**
     * methodName : findAllAddressByUserId
     * author : masiljangajji
     * description : 유저의 모든 주소를 찾음
     *
     * @return list
     */
    @RequiredAuthorization
    public List<UserAddressGetResponse> findAllUserAddress() {

        ResponseEntity<List<UserAddressGetResponse>> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL_MEMBER,
                        HttpMethod.GET,
                        new HttpEntity<>(Utils.getAuthHeader()),
                        new ParameterizedTypeReference<>() {
                        });

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
     * @param addressId id
     */
    @RequiredAuthorization
    public void deleteUserAddress(Long addressId) {

        ResponseEntity<UserAddressDeleteResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL_MEMBER_ID,
                        HttpMethod.DELETE,
                        new HttpEntity<>(Utils.getAuthHeader()),
                        new ParameterizedTypeReference<>() {
                        }, addressId);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

    }

    /**
     * methodName : createUserAddress
     * author : masiljangajji
     * description : 유저 주소를 생성
     *
     * @param userAddressCreateRequest 별명 상세주소  추가정보
     */
    @RequiredAuthorization
    public void createUserAddress(UserAddressCreateRequest userAddressCreateRequest) {

        ResponseEntity<UserAddressCreateResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL_MEMBER,
                        HttpMethod.POST,
                        new HttpEntity<>(userAddressCreateRequest, Utils.getAuthHeader()),
                        UserAddressCreateResponse.class
                );

        if (responseEntity.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException();
        }

    }

    /**
     * methodName : modifyUserAddress
     * author : masiljangajji
     * description : 유저의 주소정보를 변경 (별명,상세주소)
     *
     * @param addressId                주소 아이디
     * @param userAddressModifyRequest 별명 및 상세주소
     */
    @RequiredAuthorization
    public void modifyUserAddress(Long addressId, UserAddressModifyRequest userAddressModifyRequest) {

        ResponseEntity<UserAddressModifyResponse> responseEntity =
                restTemplate.exchange(
                        gatewayAdaptorProperties.getAddress() + URL_MEMBER_ID,
                        HttpMethod.PUT,
                        new HttpEntity<>(userAddressModifyRequest, Utils.getAuthHeader()),
                        UserAddressModifyResponse.class,
                        addressId);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }


    }


}
