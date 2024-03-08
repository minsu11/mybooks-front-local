package store.mybooks.front.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.resource.user.dto.request<br>
 * fileName       : UserOauthCreateRequest<br>
 * author         : masiljangajji<br>
 * date           : 3/6/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/6/24        masiljangajji       최초 생성
 */
@Getter
@AllArgsConstructor
public class UserOauthCreateRequest {

    private String name;

    private String phoneNumber;

    private String email;

    private String birthMonthDay;


}

