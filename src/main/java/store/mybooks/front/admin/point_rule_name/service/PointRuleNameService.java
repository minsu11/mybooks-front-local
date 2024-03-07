package store.mybooks.front.admin.point_rule_name.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.point_rule_name.adaptor.PointRuleNameAdaptor;
import store.mybooks.front.admin.point_rule_name.dto.request.PointRuleNameCreateRequest;
import store.mybooks.front.admin.point_rule_name.dto.response.PointRuleNameResponse;

/**
 * packageName    : store.mybooks.front.admin.point_rule_name.service<br>
 * fileName       : PointRuleNameService<br>
 * author         : minsu11<br>
 * date           : 3/7/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/7/24        minsu11       최초 생성<br>
 */
@Service
@RequiredArgsConstructor
public class PointRuleNameService {
    private final PointRuleNameAdaptor pointRuleNameAdaptor;

    public List<PointRuleNameResponse> getPointRuleNameList() {
        return pointRuleNameAdaptor.getPointRuleNameList();
    }

    public void createPointRuleName(PointRuleNameCreateRequest request) {
        pointRuleNameAdaptor.createPointRuleName(request);
    }
}
