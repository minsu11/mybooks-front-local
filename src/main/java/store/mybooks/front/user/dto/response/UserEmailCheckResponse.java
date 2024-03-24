package store.mybooks.front.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.resource.user.dto.response<br>
 * fileName       : UserEamilVerficatioResponse<br>
 * author         : masiljangajji<br>
 * date           : 3/24/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/24/24        masiljangajji       최초 생성
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEmailCheckResponse {

    Boolean isAvailable;
}
