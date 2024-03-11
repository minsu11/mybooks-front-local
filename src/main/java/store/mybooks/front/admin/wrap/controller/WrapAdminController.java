package store.mybooks.front.admin.wrap.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import store.mybooks.front.admin.wrap.dto.request.WrapCreateRequest;
import store.mybooks.front.admin.wrap.dto.request.WrapDeleteRequest;
import store.mybooks.front.admin.wrap.dto.request.WrapModifyRequest;
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
@RequestMapping("/admin/wraps")
public class WrapAdminController {
    private final WrapService wrapService;


    private static final String URL = "redirect:/admin/wraps";

    /**
     * methodName : viewWrap<br>
     * author : minsu11<br>
     * description : 포장지 목록을 보여주는 view
     * <br> *
     *
     * @param pageable 페이징에 대한 정보
     * @param modelMap model
     * @return string
     */
    @GetMapping
    public String viewWrap(
            @PageableDefault(size = 3) Pageable pageable,
            ModelMap modelMap) {

        PageResponse<WrapResponse> wrapPage = wrapService.getWrapPage(pageable);
        log.info("page wrap list: {}", wrapPage.getContent());

        modelMap.put("wrapList", wrapPage.getContent());

        return "admin/view/wrap-view";
    }

    /**
     * methodName : viewRegister<br>
     * author : minsu11<br>
     * description : 포장지 등록하는 {@code view}.
     * <br> *
     *
     * @param modelMap model
     * @return string
     */
    @GetMapping("/register")
    public String viewRegister(ModelMap modelMap) {
        modelMap.put("pathValue", "register");
        return "admin/view/wrap-register-view";
    }

    /**
     * methodName : doRegister<br>
     * author : minsu11<br>
     * description : 포장지 등록.
     * <br> *
     *
     * @param request
     * @return string
     */
    @PostMapping("/register")
    public String doRegister(@ModelAttribute WrapCreateRequest request) {
        String redirectUrl = URL + "/register";
        wrapService.createWrap(request, redirectUrl);
        return URL;
    }

    @PostMapping("/update-view")
    public String postUpdateForm(@ModelAttribute WrapModifyRequest request,
                                 RedirectAttributes redirectAttributes) {
        log.info("update-view: {}", request);
        redirectAttributes.addFlashAttribute("WrapModifyRequest", request);

        return URL + "/update";
    }

    @GetMapping("/update")
    public String viewUpdateForm(@ModelAttribute(name = "WrapModifyRequest") WrapModifyRequest request,
                                 ModelMap modelMap) {
        modelMap.put("pathValue", "update");
        modelMap.put("modifyWrap", request);
        return "admin/view/wrap-register-view";
    }

    @PostMapping("/update")
    public String doUpdate(@ModelAttribute WrapModifyRequest request) {
        String redirectUrl = URL + "/update";
        wrapService.updateWrap(request, redirectUrl);
        return URL;
    }

    @PostMapping("/delete")
    public String doDelete(@ModelAttribute WrapDeleteRequest request) {
        wrapService.deleteWrap(request);
        return URL;
    }
}
