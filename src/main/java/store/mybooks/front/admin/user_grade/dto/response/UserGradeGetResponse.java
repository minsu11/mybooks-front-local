package store.mybooks.front.admin.user_grade.dto.response;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.resource.user_grade.dto.response<br>
 * fileName       : UserGradeGetResponse<br>
 * author         : masiljangajji<br>
 * date           : 2/19/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/19/24        masiljangajji       최초 생성
 */


@Getter
@NoArgsConstructor
public class UserGradeGetResponse {

    Integer id;

    String userGradeNameId;

    Integer minCost;

    Integer maxCost;

    Integer rate;

    LocalDate createdDate;

}
