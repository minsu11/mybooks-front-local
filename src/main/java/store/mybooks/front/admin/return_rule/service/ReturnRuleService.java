package store.mybooks.front.admin.return_rule.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.return_rule.adaptor.ReturnRuleAdaptor;
import store.mybooks.front.admin.return_rule.dto.request.ReturnRuleCreateRequest;
import store.mybooks.front.admin.return_rule.dto.request.ReturnRuleDeleteRequest;
import store.mybooks.front.admin.return_rule.dto.request.ReturnRuleModifyRequest;
import store.mybooks.front.admin.return_rule.dto.response.ReturnRuleResponse;
import store.mybooks.front.global.exception.ManageFailedException;

/**
 * packageName    : store.mybooks.front.admin.return_rule.service<br>
 * fileName       : ReturnRuleService<br>
 * author         : minsu11<br>
 * date           : 2/28/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/28/24        minsu11       최초 생성<br>
 */
@Service
@RequiredArgsConstructor
public class ReturnRuleService {
    private final ReturnRuleAdaptor returnRuleAdaptor;

    public List<ReturnRuleResponse> getReturnRuleResponseList() {

        return returnRuleAdaptor.getReturnRuleResponseList();
    }

    public void createReturnRule(ReturnRuleCreateRequest request, String redirectUrl) {
        try {
            returnRuleAdaptor.createReturnRule(request);
        } catch (RuntimeException e) {
            throw new ManageFailedException("등록 실패", redirectUrl);
        }
    }

    public void updateReturnRule(ReturnRuleModifyRequest request, String redirectUrl) {

        try {
            returnRuleAdaptor.modifyReturnRule(request, request.getId());
        } catch (RuntimeException e) {
            throw new ManageFailedException("수정 실패", redirectUrl);
        }
    }

    public void deleteReturnRule(ReturnRuleDeleteRequest request) {
        returnRuleAdaptor.deleteReturnRule(request.getId());

    }
}
