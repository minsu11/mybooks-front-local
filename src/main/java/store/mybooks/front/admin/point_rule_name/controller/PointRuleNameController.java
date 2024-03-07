package store.mybooks.front.admin.point_rule_name.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.admin.point_rule_name.dto.request.PointRuleNameCreateRequest;
import store.mybooks.front.admin.point_rule_name.service.PointRuleNameService;

/**
 * packageName    : store.mybooks.front.admin.point_rule_name.controller<br>
 * fileName       : PointRuleNameController<br>
 * author         : minsu11<br>
 * date           : 3/7/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/7/24        minsu11       최초 생성<br>
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/point-rule-name")
public class PointRuleNameController {
    private final PointRuleNameService pointRuleNameService;

    @GetMapping("/register")
    public String viewPointRuleNameRegister() {

        return "admin/view/point-rule/point-rule-name-register-view";
    }

    @PostMapping("/register")
    public String doRegisterPointRuleName(@ModelAttribute PointRuleNameCreateRequest request) {
        pointRuleNameService.createPointRuleName(request);
        return "redirect:/admin/point-rule/register";
    }
}
