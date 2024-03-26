package store.mybooks.front.oauth;

import java.util.Arrays;
import java.util.Map;

/**
 * packageName    : store.mybooks.front.oauth2<br>
 * fileName       : OauthAttributes<br>
 * author         : masiljangajji<br>
 * date           : 3/3/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/3/24        masiljangajji       최초 생성
 */
public enum OauthAttributes {
    /**
     * The Payco.
     * payco 로그인을 지원
     * data , memeber ,idNo , email ... 은 payco 에서 응답으로 주는 Json
     * Oauth 서버로부터 받아온 json 형식의 회원 정보를 읽어 UserProfile 에 저장
     */
    PAYCO("payco") {
        @Override
        public UserProfile of(Map<String, Object> attributes) {

            // 페이코에서 보낸 json 형식을 자바 객체로 변환
            Map<String, Object> data = (Map<String, Object>) attributes.get("data");
            Map<String, Object> member = (Map<String, Object>) data.get("member");

            return UserProfile.builder()
                    .oauthId((String) member.get("idNo"))
                    .email((String) member.get("email"))
                    .name((String) member.get("name"))
                    .mobile((String) member.get("mobile"))
                    .birthday((String) member.get("birthdayMMdd"))
                    .build();
        }
    };

    private final String providerName;

    OauthAttributes(String name) {
        this.providerName = name;
    }

    /**
     * methodName : extract
     * author : masiljangajji
     * description : providername 을 이용해 어떤 서비스 제공
     *
     * @param providerName name
     * @param attributes   attributes
     * @return user profile
     */
    public static UserProfile extract(String providerName, Map<String, Object> attributes) {

        return Arrays.stream(values()) // 모든 열거 상수 배열로 반환
                .filter(provider -> providerName.equals(provider.providerName))// payco 찾기
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .of(attributes);
    }

    /**
     * methodName : of
     * author : masiljangajji
     * description :
     *
     * @param attributes attributes
     * @return user profile
     */
    public abstract UserProfile of(Map<String, Object> attributes);
}
