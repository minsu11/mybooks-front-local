package store.mybooks.front.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.auth.dto.response<br>
 * fileName       : RefreshTokenResponse<br>
 * author         : masiljangajji<br>
 * date           : 3/8/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/8/24        masiljangajji       최초 생성
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenResponse {

    private String accessToken;

}
