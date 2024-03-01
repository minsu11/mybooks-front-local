package store.mybooks.front.admin.return_rule.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import store.mybooks.front.admin.return_rule.dto.request.ReturnRuleCreateRequest;
import store.mybooks.front.admin.return_rule.dto.request.ReturnRuleDeleteRequest;
import store.mybooks.front.admin.return_rule.dto.request.ReturnRuleModifyRequest;
import store.mybooks.front.admin.return_rule.dto.request.ReturnRuleRequest;
import store.mybooks.front.admin.return_rule.dto.response.ReturnRuleResponse;
import store.mybooks.front.admin.return_rule.service.ReturnRuleService;
import store.mybooks.front.admin.return_rule_name.dto.response.ReturnRuleNameResponse;
import store.mybooks.front.admin.return_rule_name.service.ReturnRuleNameService;

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

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/return-rules")
public class ReturnRuleController {

    private final ReturnRuleService returnRuleService;
    private final ReturnRuleNameService returnRuleNameService;

    /**
     * methodName : viewReturnRule<br>
     * author : minsu11<br>
     * description : 반품 규정의 목록 관리자 view.
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
     * description : 반품 규정을 등록할 수 있는 관리자 view.
     * <br> *
     *
     * @return string
     */
    @GetMapping("/register")
    public String registerViewReturnRule(ModelMap modelMap) {
        List<ReturnRuleNameResponse> returnRuleNameList = returnRuleNameService.getReturnRuleNameList();
        modelMap.put("returnRuleNameList", returnRuleNameList);
        return "admin/view/return-rule-register-view";
    }

    /**
     * methodName : doRegister<br>
     * author : minsu11<br>
     * description : 등록 하기 기능.
     * <br> *
     *
     * @param request 등록할 반품 규정
     * @return string
     */
    @PostMapping("/register")
    public String doRegister(
            @ModelAttribute ReturnRuleCreateRequest request) {

        returnRuleService.createReturnRule(request);
        return "redirect:/admin/return-rules";
    }

    /**
     * methodName : viewUpdateForm<br>
     * author : minsu11<br>
     * description : 반품 규정 수정하는 {@code view}로 {@code redirect}시킴.
     * <br> *
     *
     * @param request            수정할 반품 규정
     * @param redirectAttributes {@code redirect}로 요청 보낼 모델
     * @return string
     */
    @PostMapping("/update-form")
    public String viewUpdateForm(
            @ModelAttribute ReturnRuleRequest request,
            RedirectAttributes redirectAttributes) {
        log.info("update 전:{}", request);
        redirectAttributes.addFlashAttribute("returnRuleModifyRequest", request);
        log.info("update 후:{}", request);

        return "redirect:/admin/return-rules/update-form";
    }

    /**
     * methodName : view<br>
     * author : minsu11<br>
     * description : 반품 규정 수정하는 {@code view}.
     * <br> *
     *
     * @param modelMap {@code view}에 전달할 모델
     * @param request  수정할 반품 규정
     * @return string
     */
    @GetMapping("/update-form")
    public String view(ModelMap modelMap, @ModelAttribute(name = "returnRuleModifyRequest") ReturnRuleRequest request) {
        List<ReturnRuleNameResponse> returnRuleNameList = returnRuleNameService.getReturnRuleNameList();

        log.info("request value: {}", request.getId());
        modelMap.put("returnRuleNameList", returnRuleNameList);
        modelMap.put("modifyReturnRule", request);
        return "admin/view/return-rule-register-view";
    }

    /**
     * methodName : doUpdate<br>
     * author : minsu11<br>
     * description : 반품 규정 수정.
     * <br> *
     *
     * @param request            수정할 반품 규정
     * @param redirectAttributes redirect 보낼 데이터
     * @return string
     */
    @PostMapping("/update")
    public String doUpdate(
            @ModelAttribute ReturnRuleModifyRequest request,
            RedirectAttributes redirectAttributes) {
        returnRuleService.updateReturnRule(request);

        redirectAttributes.addFlashAttribute("modifyReturnRule", request);
        return "redirect:/admin/return-rules";
    }


    /**
     * methodName : doDelete<br>
     * author : minsu11<br>
     * description : 반품 규정 삭제.
     * <br> *
     *
     * @param request 삭제할 반품 규정
     * @return string
     */
    @PostMapping("/delete")
    public String doDelete(@ModelAttribute ReturnRuleDeleteRequest request) {
        returnRuleService.deleteReturnRule(request);

        return "redirect:/admin/return-rules";
    }


}
