package store.mybooks.front.admin.wrap.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.admin.wrap.dto.response.WrapResponse;
import store.mybooks.front.admin.wrap.service.WrapService;
import store.mybooks.front.pageable.dto.response.PageResponse;

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
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("admin/wraps")
public class WrapAdminController {
    private final WrapService wrapService;

    @GetMapping
    public String viewWrap(
            @PageableDefault(size = 2) Pageable pageable,
            ModelMap modelMap) {

        PageResponse<WrapResponse> wrapPage = wrapService.getWrapPage(pageable);
        log.info("page wrap list: {}", wrapPage.getContent());

        modelMap.put("wrapList", wrapPage.getContent());

        return "admin/view/wrap-view";
    }
}
