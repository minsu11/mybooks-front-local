package store.mybooks.front.user.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.resource.user.dto.response<br>
 * fileName       : UserCreateResponse<br>
 * author         : masiljangajji<br>
 * date           : 2/13/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/13/24        masiljangajji       최초 생성
 */

@Getter
@NoArgsConstructor
public class UserCreateResponse {

    private String name;

    private String email;

    private Integer birthYear;

    private String birthMonthDay;

    private String userStatusName;

    private String userGradeName;




}
