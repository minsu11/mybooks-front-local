package store.mybooks.front.home.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.home.dto.response<br>
 * fileName       : UserCheckResponse<br>
 * author         : minsu11<br>
 * date           : 3/27/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/27/24        minsu11       최초 생성<br>
 */
@Getter
@AllArgsConstructor
public class UserCheckResponse {
    private Boolean checkResult;

    public void changeCheckResult(Boolean checkResult) {
        this.checkResult = checkResult;
    }
}
