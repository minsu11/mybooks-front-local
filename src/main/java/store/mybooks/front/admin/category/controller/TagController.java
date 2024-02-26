package store.mybooks.front.admin.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import store.mybooks.front.admin.category.model.request.TagCreateRequest;
import store.mybooks.front.admin.category.model.request.TagModifyRequest;
import store.mybooks.front.admin.category.service.TagService;

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
     * @param page  Integer
     * @param size  Integer
     * @param model Model
     * @return string
     */
    @GetMapping
    public String getTagPage(@RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "size", required = false) Integer size,
                             Model model) {
        if (page == null) {
            page = 0;
        }

        if (size == null) {
            size = 10;
        }

        model.addAttribute("tags", tagService.getTags(page, size).getContent());

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
    public String createTag(@ModelAttribute TagCreateRequest tagCreateRequest) {
        tagService.createTag(tagCreateRequest);
        return "redirect:/admin/tag/register";
    }

    @GetMapping("/update")
    public String getUpdatePage(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return "/admin/view/tag-update";
    }

    @PostMapping("/update/{id}")
    public String updateTag(@PathVariable("id") Integer id, @ModelAttribute TagModifyRequest tagModifyRequest) {
        tagService.updateTag(id, tagModifyRequest);
        return "redirect:/admin/tag";
    }


    @GetMapping("/delete/{id}")
    public String deleteTag(@PathVariable("id") Integer id) {
        tagService.deleteTag(id);
        return "redirect:/admin/tag";
    }
}
