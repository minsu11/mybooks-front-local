package store.mybooks.front.admin.point_rule.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.admin.point_rule.dto.request<br>
 * fileName       : PointRuleCreateRequest<br>
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
public class PointRuleCreateRequest {
    @NotBlank
    @Size(min = 5, max = 50)
    private String pointRuleName;

    @PositiveOrZero
    @Max(100)
    private Integer rate;

    @PositiveOrZero
    @Max(10000)
    private Integer cost;
}
