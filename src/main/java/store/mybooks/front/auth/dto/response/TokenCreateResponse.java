package store.mybooks.front.auth.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.jwt.dto.request.reponse<br>
 * fileName       : TokenResponse<br>
 * author         : masiljangajji<br>
 * date           : 2/28/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/28/24        masiljangajji       최초 생성
 */

@Getter
@NoArgsConstructor
public class TokenCreateResponse {


    private String accessToken;


}
