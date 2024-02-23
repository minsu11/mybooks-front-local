package store.mybooks.front.user.dto.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : store.mybooks.resource.user.dto.response
 * fileName       : UserCreateResponse
 * author         : masiljangajji
 * date           : 2/13/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/13/24        masiljangajji       최초 생성
 */

@Getter
@Setter
@AllArgsConstructor
public class UserCreateResponse {

    private String name;

    private String email;

    private LocalDate birth;

    private String userStatusName;

    private String userGradeName;


}
