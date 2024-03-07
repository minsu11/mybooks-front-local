package store.mybooks.front.admin.point_rule.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import store.mybooks.front.admin.point_rule.dto.request.PointRuleCreateRequest;
import store.mybooks.front.admin.point_rule.dto.request.PointRuleModifyRequest;
import store.mybooks.front.admin.point_rule.dto.request.PointRuleRequest;
import store.mybooks.front.admin.point_rule.dto.response.PointRuleResponse;
import store.mybooks.front.admin.point_rule.service.PointRuleService;
import store.mybooks.front.admin.point_rule_name.dto.response.PointRuleNameResponse;
import store.mybooks.front.admin.point_rule_name.service.PointRuleNameService;
import store.mybooks.front.pageable.dto.response.PageResponse;

/**
 * packageName    : store.mybooks.front.admin.point_rule.controller<br>
 * fileName       : PointRuleController<br>
 * author         : minsu11<br>
 * date           : 3/7/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/7/24        minsu11       최초 생성<br>
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/point-rule")
public class PointRuleController {
    private final PointRuleService pointRuleService;
    private final PointRuleNameService pointRuleNameService;

    /**
     * methodName : viewPointRule<br>
     * author : minsu11<br>
     * description : 관리자 페이지에서 페이징 처리한 포인트 규정 목록을 보여주는 {@code view}.
     * <br> *
     *
     * @param modelMap model
     * @param pageable 페이징
     * @return string
     */
    @GetMapping
    public String viewPointRule(ModelMap modelMap, @PageableDefault Pageable pageable) {
        PageResponse<PointRuleResponse> pageResponse = pointRuleService.getPointResponsePage(pageable);
        modelMap.put("pointRulePage", pageResponse);
        return "admin/view/point-rule/point-rule-view";
    }

    /**
     * methodName : viewpointRuleRegister<br>
     * author : minsu11<br>
     * description : 포인트 규정 등록 {@code view}.
     * <br> *
     *
     * @param modelMap 모델
     * @return string
     */
    @GetMapping("/register")
    public String viewPointRuleRegister(ModelMap modelMap) {
        List<PointRuleNameResponse> pointRuleResponseList = pointRuleNameService.getPointRuleNameList();

        modelMap.put("pointRuleNameList", pointRuleResponseList);

        return "admin/view/point-rule/point-rule-register-view";
    }

    @PostMapping("/register")
    public String doRegisterPointRule(@ModelAttribute PointRuleCreateRequest request) {
        pointRuleService.createPointRule(request);
        return "redirect:/admin/point-rule";
    }

    @PostMapping("/update-form")
    public String postPointRuleUpdate(@ModelAttribute PointRuleRequest request,
                                      RedirectAttributes redirectAttributes) {
        log.info("포인트 규정 명 등록: {}", request.getPointRuleName());

        redirectAttributes.addFlashAttribute("modifyPointRule", request);
        return "redirect:/admin/point-rule/update-form";
    }

    @GetMapping("/update-form")
    public String viewPointRuleUpdate(@ModelAttribute(name = "modifyPointRule") PointRuleRequest request,
                                      ModelMap modelMap) {
        log.info("포인트 규정 명 등록: {}", request.getPointRuleName());

        List<PointRuleNameResponse> pointRuleResponseList = pointRuleNameService.getPointRuleNameList();
        log.info("수정하기 위한 값:{}", request.getPointRuleName());
        modelMap.put("pointRuleNameList", pointRuleResponseList);
        modelMap.put("modifyPointRule", request);
        return "admin/view/point-rule/point-rule-register-view";
    }

    @PostMapping("update/{id}")
    public String doUpdatePointRule(@ModelAttribute PointRuleModifyRequest request,
                                    @PathVariable Integer id) {
        pointRuleService.modifyPointRule(request, id);
        return "redirect:/admin/point-rule";
    }

    @PostMapping("/{id}")
    public String doDeletePointRule(@PathVariable Integer id) {
        pointRuleService.deletePointRule(id);
        return "redirect:/admin/point-rule";
    }
}
