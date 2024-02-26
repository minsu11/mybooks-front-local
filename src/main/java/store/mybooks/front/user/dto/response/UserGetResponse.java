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

    private String userGradeUserGradeNameId;

    private String userStatusId;
    private String name;

    private String phoneNumber;

    private String email;

    private LocalDate birth;

    private LocalDateTime createdAt;

    private LocalDateTime latestLogin;

    private LocalDate gradeChangedDate;


}
