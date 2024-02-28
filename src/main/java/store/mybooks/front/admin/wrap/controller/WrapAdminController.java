package store.mybooks.front.admin.wrap.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.admin.wrap.service.WrapService;

/**
 * packageName    : store.mybooks.front.admin.wrap.controller<br>
 * fileName       : WrapController<br>
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
@RequestMapping("admin/wraps")
public class WrapAdminController {
    private final WrapService wrapService;

    @GetMapping
    public String viewWrap(
            @PageableDefault Pageable pageable,
            ModelMap modelMap) {
        return "admin/view/wrap-view";
    }
}
