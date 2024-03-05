package store.mybooks.front.admin.category.controller;

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
import store.mybooks.front.admin.category.model.request.CategoryCreateRequest;
import store.mybooks.front.admin.category.model.request.CategoryModifyRequest;
import store.mybooks.front.admin.category.model.response.CategoryGetResponseForUpdate;
import store.mybooks.front.admin.category.service.CategoryService;
import store.mybooks.front.global.exception.ValidationFailException;

/**
 * packageName    : store.mybooks.front.admin.controller
 * fileName       : CategoryController
 * author         : damho-lee
 * date           : 2/23/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/23/24          damho-lee          최초 생성
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/category")
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * methodName : getCategoryPage <br>
     * author : damho-lee <br>
     * description : 사이드바에서 카테고리를 눌렀을 때 페이지.<br>
     *
     * @param pageable Pageable
     * @param model    Model
     * @return string
     */
    @GetMapping
    public String getCategoryPage(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("categories", categoryService.getCategories(pageable).getContent());
        return "admin/view/category";
    }

    /**
     * methodName : getCategoryRegisterPage <br>
     * author : damho-lee <br>
     * description : 카테고리 등록 페이지.<br>
     *
     * @param model Model
     * @return string
     */
    @GetMapping("/register")
    public String getCategoryRegisterPage(Model model) {
        model.addAttribute(categoryService.getHighestCategories());
        return "admin/view/category-register";
    }

    /**
     * methodName : categoryRegister <br>
     * author : damho-lee <br>
     * description : 카테고리 등록 요청을 받음. 성공적으로 처리되면 카테고리 등록 페이지로 리다이텍트함.<br>
     *
     * @param categoryCreateRequest CategoryCreateRequest
     * @return string
     */
    @PostMapping("/register")
    public String categoryRegister(@Valid @ModelAttribute CategoryCreateRequest categoryCreateRequest,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailException(bindingResult);
        }
        categoryService.createCategory(categoryCreateRequest);
        return "redirect:/admin/category/register";
    }

    /**
     * methodName : deleteCategory <br>
     * author : damho-lee <br>
     * description : 카테고리 삭제 요청을 처리. 성공적으로 처리되면 카테고리 페이지로 리다이렉트<br>
     *
     * @param id Integer
     * @return string
     */
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/category";
    }

    /**
     * methodName : getUpdateForm <br>
     * author : damho-lee <br>
     * description : 카테고리 수정 페이지. resource 서버에서 id 를 통해 데이터를 받아와서 model 에 넣어준다.<br>
     *
     * @param id    Integer
     * @param model Model
     * @return string
     */
    @GetMapping("/update")
    public String getUpdateForm(@RequestParam("id") Integer id, Model model) {
        CategoryGetResponseForUpdate categoryGetResponseForUpdate = categoryService.getCategory(id);
        model.addAttribute("category", categoryGetResponseForUpdate.getTargetCategory());
        model.addAttribute("levelOneCategoryName", categoryGetResponseForUpdate.getLevelOneCategoryName());
        model.addAttribute("levelTwoCategoryName", categoryGetResponseForUpdate.getLevelTwoCategoryName());

        return "admin/view/category-update";
    }

    /**
     * methodName : updateCategory <br>
     * author : damho-lee <br>
     * description : 카테고리 수정 요청 처리. 성공적으로 처리되면 카테고리 페이지로 리다이렉트.<br>
     *
     * @param categoryModifyRequest CategoryModifyRequest
     * @return string
     */
    @PostMapping("/update")
    public String updateCategory(@ModelAttribute CategoryModifyRequest categoryModifyRequest) {
        categoryService.updateCategory(categoryModifyRequest);
        return "redirect:/admin/category";
    }
}
