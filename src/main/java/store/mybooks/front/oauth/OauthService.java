package store.mybooks.front.oauth;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import store.mybooks.front.config.KeyConfig;
import store.mybooks.front.user.adaptor.UserAdaptor;
import store.mybooks.front.user.dto.request.UserOauthCreateRequest;
import store.mybooks.front.user.dto.request.UserOauthLoginRequest;
import store.mybooks.front.user.dto.response.UserLoginResponse;
import store.mybooks.front.user.dto.response.UserOauthCreateResponse;

/**
 * packageName    : store.mybooks.front.oauth2<br>
 * fileName       : OauthService<br>
 * author         : masiljangajji<br>
 * date           : 3/3/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/3/24        masiljangajji       최초 생성
 */
@Service
@RequiredArgsConstructor
public class OauthService {

    private final InMemoryProviderRepository inMemoryProviderRepository;

    private final UserAdaptor userAdaptor;

    private final KeyConfig keyConfig;

    private OauthTokenResponse getToken(String code, OauthProvider provider) {

        return WebClient.create()
                .post()
                .uri(provider.getTokenUrl())
                .bodyValue(tokenRequest(code, provider))
                .retrieve()
                .bodyToMono(OauthTokenResponse.class)
                .block();
    }

    // 바디부에 필요한 정보들 추가 , 페이코 api 양식에 맞춰서
    private MultiValueMap<String, String> tokenRequest(String code, OauthProvider provider) {

        System.out.println(code);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", keyConfig.keyStore(provider.getClientId()));
        formData.add("client_secret", keyConfig.keyStore(provider.getClientSecret()));
        formData.add("grant_type", "authorization_code"); // 이건 고정값
        formData.add("code", code); // 페이코가 준 코드
        formData.add("redirect_uri", keyConfig.keyStore(provider.getRedirectUrl()));
        return formData;
    }

    /**
     * methodName : login
     * author : masiljangajji
     * description :
     *
     * @param code
     * @return oauth login response
     */
    public UserLoginResponse oauthLogin(String providerName, String code) {

        // 프론트에서 넘어온 provider 이름을 통해 InMemoryProviderRepository에서 OauthProvider 가져오기
        OauthProvider provider = inMemoryProviderRepository.findByProviderName(providerName);

        // access token 가져오기
        OauthTokenResponse tokenResponse = getToken(code, provider);

        // 유저 정보 가져오기
        UserProfile userProfile = getUserProfile(providerName, tokenResponse, provider);

        // 가져온걸로 db 긁어서 있는회원인지 확인
        UserLoginResponse userLoginResponse =
                userAdaptor.loginOauthUser(new UserOauthLoginRequest(userProfile.getEmail()));

        if (userLoginResponse.getIsValidUser()) { // 이미 존재하는 회원이면 그대로 로그인
            return userLoginResponse;
        }

        // 존재하는 회원 아니면 회원가입 시키기
        UserOauthCreateRequest createRequest =
                new UserOauthCreateRequest(userProfile.getName(), userProfile.getMobile(), userProfile.getEmail(),
                        userProfile.getBirthday());

        UserOauthCreateResponse createResponse=userAdaptor.createOauthUser(createRequest);

        return new UserLoginResponse(true,false,createResponse.getId(),createResponse.getUserStatusName());

    }

    private UserProfile getUserProfile(String providerName, OauthTokenResponse tokenResponse, OauthProvider provider) {
        // 페이코에서 받아온 json 형식의 회원정보를 파싱하여 자바 객체로 변환한 결과값을 반환받음
        Map<String, Object> userAttributes = getUserAttributes(provider, tokenResponse);
        return OauthAttributes.extract(providerName, userAttributes);
    }

    // 페이코에서 받은 엑세스토큰 , 클라이언트아이디로 회원정보 받아오는 요청보냄
    private Map<String, Object> getUserAttributes(OauthProvider provider, OauthTokenResponse tokenResponse) {
        return WebClient.create()
                .post()
                .uri(provider.getUserInfoUrl())
                .headers(headers -> {
                    headers.set("access_token", tokenResponse.getAccessToken());
                    headers.set("client_id", keyConfig.keyStore(provider.getClientId()));
                })
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .block();
    }

}