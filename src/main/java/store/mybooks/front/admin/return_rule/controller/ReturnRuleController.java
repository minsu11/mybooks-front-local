package store.mybooks.front.admin.return_rule.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.admin.return_rule.dto.request.ReturnRuleCreateRequest;
import store.mybooks.front.admin.return_rule.dto.response.ReturnRuleResponse;
import store.mybooks.front.admin.return_rule.service.ReturnRuleService;

/**
 * packageName    : store.mybooks.front.admin.return_rule.controller<br>
 * fileName       : ReturnRuleController<br>
 * author         : minsu11<br>
 * date           : 2/28/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/28/24        minsu11       최초 생성<br>
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/return-rules")
public class ReturnRuleController {
    private final ReturnRuleService returnRuleService;

    @GetMapping
    public String viewReturnRule(ModelMap modelMap) {
        List<ReturnRuleResponse> returnRuleResponseList = returnRuleService.getReturnRuleResponseList();
        modelMap.put("returnRuleList", returnRuleResponseList);
        return "admin/view/return-rule-view";
    }

    @GetMapping("/register")
    public String registerViewReturnRule(ModelMap modelMap) {
        modelMap.put("pathValue", "register");

        return "admin/view/return-rule-register-view";
    }

    @PostMapping("register")
    public String doRegister(
            ModelMap modelMap,
            @ModelAttribute ReturnRuleCreateRequest request) {
        if (returnRuleService.createResponse(request)) {

            return "redirect:/admin/return-rules";
        }
        modelMap.put("registerReturnRule", request);
        return "redirect:/admin/return-rules/register";

    }
}
