package store.mybooks.front.user.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.resource.user.dto.response<br>
 * fileName       : UserEmailResponse<br>
 * author         : masiljangajji<br>
 * date           : 3/6/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/6/24        masiljangajji       최초 생성
 */

@NoArgsConstructor
@Getter
public class UserEncryptedPasswordResponse {

    private String encryptedPassword;

}
