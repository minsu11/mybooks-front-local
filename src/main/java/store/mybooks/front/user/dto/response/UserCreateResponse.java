package store.mybooks.front.user.dto.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Setter
@NoArgsConstructor
public class UserCreateResponse {

    private String name;

    private String email;

    private Integer birthYear;

    private String birthMonthDay;

    private String userStatusName;

    private String userGradeName;


}
