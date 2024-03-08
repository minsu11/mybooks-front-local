package store.mybooks.front.admin.point_rule.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.admin.point_rule.dto.request<br>
 * fileName       : PointRuleModifyRequest<br>
 * author         : minsu11<br>
 * date           : 3/7/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/7/24        minsu11       최초 생성<br>
 */
@Getter
@AllArgsConstructor
public class PointRuleModifyRequest {
    private String pointRuleName;
    private Integer rate;
    private Integer cost;

}
