package store.mybooks.front.admin.return_rule_name.dto.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : store.mybooks.front.admin.return_rule_name.dto.response<br>
 * fileName       : ReturnRuleNameResponse<br>
 * author         : minsu11<br>
 * date           : 3/1/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/1/24        minsu11       최초 생성<br>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnRuleNameResponse {
    private String id;
    private LocalDate createdDate;
}
