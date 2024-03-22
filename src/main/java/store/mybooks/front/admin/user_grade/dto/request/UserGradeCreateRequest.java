package store.mybooks.front.admin.user_grade.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.admin.user_grade.dto.request<br>
 * fileName       : UserGradeCreateRequest<br>
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
public class UserGradeCreateRequest {

    private Integer rate;

    private String userGradeNameId;

}
