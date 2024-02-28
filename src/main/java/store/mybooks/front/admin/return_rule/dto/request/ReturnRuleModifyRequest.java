package store.mybooks.front.admin.return_rule.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.admin.return_rule.dto.request<br>
 * fileName       : ReturnRuleModifyRequset<br>
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
public class ReturnRuleModifyRequest {
    private String returnName;
    private Integer deliveryFee;
    private Integer term;
    
}
