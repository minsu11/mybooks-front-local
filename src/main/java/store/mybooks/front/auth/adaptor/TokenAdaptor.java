package store.mybooks.front.auth.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.auth.dto.request.LogoutRequest;
import store.mybooks.front.auth.dto.request.RefreshTokenRequest;
import store.mybooks.front.auth.dto.request.TokenCreateRequest;
import store.mybooks.front.auth.dto.response.RefreshTokenResponse;
import store.mybooks.front.auth.dto.response.TokenCreateResponse;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.utils.Utils;

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

    /**
     * methodName : createToken
     * author : masiljangajji
     * description : jwt 생성요청을 보냄
     *
     * @param tokenCreateRequest 유저의 정보를 담은 dto , 이것을 기반으로 JWT 생성
     * @return token create response
     */
    public TokenCreateResponse createToken(TokenCreateRequest tokenCreateRequest) {

        ResponseEntity<TokenCreateResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/auth", HttpMethod.POST,
                        new HttpEntity<>(tokenCreateRequest, Utils.getHttpHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        if (responseEntity.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException();
        }

        return responseEntity.getBody();
    }

    /**
     * methodName : refreshAccessToken
     * author : masiljangajji
     * description : 엑세스토큰 재발급 요청을 보냄
     *
     * @param refreshTokenRequest accessToken , ip , X-User-Agent 정보
     * @return refresh token response
     */
    public RefreshTokenResponse refreshAccessToken(RefreshTokenRequest refreshTokenRequest) {

        ResponseEntity<RefreshTokenResponse> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/auth/refresh", HttpMethod.POST,
                        new HttpEntity<>(refreshTokenRequest, Utils.getHttpHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException();
        }

        return responseEntity.getBody();
    }

    /**
     * methodName : deleteRefreshToken
     * author : masiljangajji
     * description : 로그아웃시 리프래시토큰 삭제 요청을 보냄
     *
     * @param logoutRequest 엑세스토큰 , ip , X-User-Agent
     */
    public void deleteRefreshToken(LogoutRequest logoutRequest){

        ResponseEntity<Void> responseEntity =
                restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/auth/logout", HttpMethod.DELETE,
                        new HttpEntity<>(logoutRequest, Utils.getHttpHeader()),
                        new ParameterizedTypeReference<>() {
                        });


        if (responseEntity.getStatusCode() != HttpStatus.NO_CONTENT) {
            throw new RuntimeException();
        }
    }


}
