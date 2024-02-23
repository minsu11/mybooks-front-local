package store.mybooks.front.user.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.resource.user.dto.request
 * fileName       : UserModifyRequest
 * author         : masiljangajji
 * date           : 2/13/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/13/24        masiljangajji       최초 생성
 */

@Getter
@AllArgsConstructor
public class UserModifyRequest {

    private String name;
    private String password;
    private String userStatusName;
    private String userGradeName;
    private String phoneNumber;
    private LocalDateTime latestLogin;
    private LocalDateTime deletedAt;
    private LocalDate gradeChangeDate;

}

