package store.mybooks.front.admin.return_rule.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.admin.return_rule.dto.response<br>
 * fileName       : ReturnRuleResponse<br>
 * author         : minsu11<br>
 * date           : 2/28/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/28/24        minsu11       최초 생성<br>
 */
@Getter
@AllArgsConstructor
public class ReturnRuleResponse {
    private Long id;
    private String returnName;
    private Integer deliveryFee;
    private Integer term;
    private Boolean isAvailable;
}
