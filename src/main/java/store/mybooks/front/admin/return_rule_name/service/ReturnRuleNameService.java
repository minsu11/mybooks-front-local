package store.mybooks.front.admin.return_rule_name.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.return_rule_name.adaptor.ReturnRuleNameAdaptor;
import store.mybooks.front.admin.return_rule_name.dto.response.ReturnRuleNameResponse;

/**
 * packageName    : store.mybooks.front.admin.return_rule_name.service<br>
 * fileName       : ReturnRuleNameService<br>
 * author         : minsu11<br>
 * date           : 3/1/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/1/24        minsu11       최초 생성<br>
 */
@Service
@RequiredArgsConstructor
public class ReturnRuleNameService {
    private final ReturnRuleNameAdaptor returnRuleNameAdaptor;

    public List<ReturnRuleNameResponse> getReturnRuleNameList() {
        return returnRuleNameAdaptor.getReturnRuleList();
    }
}
