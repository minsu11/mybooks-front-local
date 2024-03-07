package store.mybooks.front.admin.point_rule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.admin.point_rule.dto.response.PointRuleResponse;
import store.mybooks.front.admin.point_rule.service.PointRuleService;
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
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/point")
public class PointRuleController {
    private final PointRuleService pointRuleService;

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
}
