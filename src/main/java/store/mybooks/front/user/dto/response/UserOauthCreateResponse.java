package store.mybooks.front.user.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.oauth.dto.request.dto.response<br>
 * fileName       : UserOauthCreateResponse<br>
 * author         : masiljangajji<br>
 * date           : 3/6/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/6/24        masiljangajji       최초 생성
 */
@Getter
@NoArgsConstructor
public class UserOauthCreateResponse {

    private String name;

    private String email;

    private String birthMonthDay;

    private String userStatusName;

    private String userGradeName;

    private Long id;

}
