package store.mybooks.front.user.dto.request;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * packageName    : store.mybooks.front.user.dto.request<br>
 * fileName       : UserSocialModifyRequest<br>
 * author         : masiljangajji<br>
 * date           : 3/22/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/22/24        masiljangajji       최초 생성
 */
@Getter
@AllArgsConstructor
public class UserOauthRequest {

    private String name;

    private String email;

    private String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    private String oauthId;

}
