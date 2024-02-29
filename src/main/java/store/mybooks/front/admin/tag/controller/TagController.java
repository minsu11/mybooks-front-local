package store.mybooks.front.admin.tag.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import store.mybooks.front.admin.tag.model.request.TagCreateRequest;
import store.mybooks.front.admin.tag.model.request.TagModifyRequest;
import store.mybooks.front.admin.tag.service.TagService;
import store.mybooks.front.global.exception.ValidationFailException;

/**
 * packageName    : store.mybooks.front.category.controller
 * fileName       : TagController
 * author         : damho-lee
 * date           : 2/26/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/26/24          damho-lee          최초 생성
 */
@Controller
@RequestMapping("/admin/tag")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    /**
     * methodName : getTagPage <br>
     * author : damho-lee <br>
     * description : 사이드바에서 태그를 누르는 경우 나오는 페이지.<br>
     *
     * @param pageable Pageable
     * @param model Model
     * @return string
     */
    @GetMapping
    public String getTagPage(@PageableDefault Pageable pageable,
                             Model model) {
        model.addAttribute("tags", tagService.getTags(pageable));

        return "/admin/view/tag";
    }

    /**
     * methodName : getTagRegisterPage <br>
     * author : damho-lee <br>
     * description : 태그 등록 페이지.<br>
     *
     * @return string
     */
    @GetMapping("/register")
    public String getTagRegisterPage() {
        return "/admin/view/tag-register";
    }

    /**
     * methodName : createTag <br>
     * author : damho-lee <br>
     * description : 태그 등록 요청 처리. 성공적으로 처리하면 태그 등록페이지로 리다이렉트. <br>
     *
     * @param tagCreateRequest TagCreateRequest
     * @return string
     */
    @PostMapping("/register")
    public String createTag(@ModelAttribute TagCreateRequest tagCreateRequest,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailException(bindingResult);
        }
        
        tagService.createTag(tagCreateRequest);
        return "redirect:/admin/tag/register";
    }

    /**
     * methodName : getUpdatePage <br>
     * author : damho-lee <br>
     * description : 태그 수정 페이지.<br>
     *
     * @param id Integer
     * @param model Model
     * @return string
     */
    @GetMapping("/update")
    public String getUpdatePage(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return "/admin/view/tag-update";
    }

    /**
     * methodName : updateTag <br>
     * author : damho-lee <br>
     * description : 태그 수정 요청.<br>
     *
     * @param id Integer
     * @param tagModifyRequest TagModifyRequest
     * @return string
     */
    @PostMapping("/update/{id}")
    public String updateTag(@PathVariable("id") Integer id, @Valid @ModelAttribute TagModifyRequest tagModifyRequest) {
        tagService.updateTag(id, tagModifyRequest);
        return "redirect:/admin/tag";
    }


    /**
     * methodName : deleteTag <br>
     * author : damho-lee <br>
     * description : 태그 삭제 요청.<br>
     *
     * @param id Integer
     * @return string
     */
    @GetMapping("/delete/{id}")
    public String deleteTag(@PathVariable("id") Integer id) {
        tagService.deleteTag(id);
        return "redirect:/admin/tag";
    }
}
