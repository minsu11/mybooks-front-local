package store.mybooks.front.user.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName    : store.mybooks.resource.user.dto.response
 * fileName       : UserGetResponse
 * author         : masiljangajji
 * date           : 2/16/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/16/24        masiljangajji       최초 생성
 */


@Getter
@NoArgsConstructor
public class UserGetResponse {

    String userGradeUserGradeNameId;

    String userStatusId;
    String name;

    String phoneNumber;

    String email;

    LocalDate birth;

    LocalDateTime createdAt;

    LocalDateTime latestLogin;

    LocalDate gradeChangedDate;


}
