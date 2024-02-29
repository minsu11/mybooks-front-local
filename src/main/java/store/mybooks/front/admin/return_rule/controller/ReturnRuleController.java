package store.mybooks.front.admin.return_rule.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import store.mybooks.front.admin.return_rule.dto.request.ReturnRuleCreateRequest;
import store.mybooks.front.admin.return_rule.dto.request.ReturnRuleDeleteRequest;
import store.mybooks.front.admin.return_rule.dto.request.ReturnRuleModifyRequest;
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

    /**
     * methodName : viewReturnRule<br>
     * author : minsu11<br>
     * description : 반품 규정의 목록 관리자 view
     * <br> *
     *
     * @param modelMap view에 보여줄 데이터
     * @return string
     */
    @GetMapping
    public String viewReturnRule(ModelMap modelMap) {
        List<ReturnRuleResponse> returnRuleResponseList = returnRuleService.getReturnRuleResponseList();
        modelMap.put("returnRuleList", returnRuleResponseList);
        return "admin/view/return-rule-view";
    }

    /**
     * methodName : registerViewReturnRule<br>
     * author : minsu11<br>
     * description : 반품 규정을 등록할 수 있는 관리자 view
     * <br> *
     *
     * @return string
     */
    @GetMapping("/register")
    public String registerViewReturnRule() {

        return "admin/view/return-rule-register-view";
    }

    /**
     * methodName : doRegister<br>
     * author : minsu11<br>
     * description : 등록 하기 기능
     * <br> *
     *
     * @param redirectAttributes
     * @param request
     * @return string
     */
    @PostMapping("/register")
    public String doRegister(
            RedirectAttributes redirectAttributes,
            @ModelAttribute ReturnRuleCreateRequest request) {
        if (returnRuleService.createReturnRule(request)) {

            return "redirect:/admin/return-rules";
        }
        redirectAttributes.addFlashAttribute("registerReturnRule", request);
        return "redirect:/admin/return-rules/register";
    }

    @PostMapping("/update-form/{id}")
    public String viewUpdateForm(
            @PathVariable Integer id,
            @ModelAttribute ReturnRuleModifyRequest request,
            HttpSession session) {
        session.setAttribute("id", id);
        session.setAttribute("modifyRuleModifyRequest", request);

        return "redirect:/admin/return-rules/update-form";
    }

    @GetMapping("/update-form")
    public String view(HttpSession session, ModelMap modelMap) {
        Integer id = (Integer) session.getAttribute("id");
        ReturnRuleModifyRequest request =
                (ReturnRuleModifyRequest) session.getAttribute("modifyRuleModifyRequest");
        modelMap.put("id", id);
        modelMap.put("modifyReturnRule", request);
        return "admin/view/return-rule-register-view";
    }

    @PostMapping("/update/{id}")
    public String doUpdate(@PathVariable Integer id,
                           @ModelAttribute ReturnRuleModifyRequest request,
                           RedirectAttributes redirectAttributes) {
        if (Boolean.TRUE.equals(returnRuleService.updateReturnRule(request, id))) {
            return "redirect:/admin/return-rules";
        }
        redirectAttributes.addFlashAttribute("id", id);
        redirectAttributes.addFlashAttribute("modifyReturnRule", request);
        return "redirect:/admin/return-rules/update/fail";
    }

    @GetMapping("/update/fail")
    public String updateFail() {
        return "admin/view/return-rule-view";
    }

    @PostMapping("/delete")
    public String doDelete(@ModelAttribute ReturnRuleDeleteRequest request) {
        returnRuleService.deleteReturnRule(request);

        return "redirect:/admin/return-rules";
    }


}
