package store.mybooks.front.oauth2;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.oauth2<br>
 * fileName       : OauthLoginResponse<br>
 * author         : masiljangajji<br>
 * date           : 3/3/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/3/24        masiljangajji       최초 생성
 */
@Getter
@NoArgsConstructor
public class OauthLoginResponse {
    private Long id;
    private String name;
    private String email;
    private String tokenType;
    private String accessToken;
    private String refreshToken;

    @Builder
    public OauthLoginResponse(Long id, String name, String email, String imageUrl, String tokenType, String accessToken,
                              String refreshToken) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}