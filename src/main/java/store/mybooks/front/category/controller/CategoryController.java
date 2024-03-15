package store.mybooks.front.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.category.model.reesponse.CategoryGetResponseForCategoryView;
import store.mybooks.front.category.service.CategoryService;

/**
 * packageName    : store.mybooks.front.category.controller
 * fileName       : CategoryController
 * author         : damho-lee
 * date           : 3/13/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/13/24          damho-lee          최초 생성
 */
@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/category-display/{categoryId}")
    public String getCategoryDisplayPage(@PathVariable("categoryId") Integer categoryId,
                                         @PageableDefault(size = 9) Pageable pageable,
                                         Model model) {
        CategoryGetResponseForCategoryView categoryGetResponseForCategoryView =
                categoryService.getCategoriesForCategoryView(categoryId);

        model.addAttribute("highestCategoryName", categoryGetResponseForCategoryView.getHighestCategoryName());
        model.addAttribute("name", categoryGetResponseForCategoryView.getName());
        model.addAttribute("levelTwoCategories", categoryGetResponseForCategoryView.getLevelTwoCategories());
        model.addAttribute("targetCategories", categoryGetResponseForCategoryView.getTargetCategories());
        model.addAttribute("books", categoryService.getBooksForCategoryView(categoryId, pageable));

        return "category-display";
    }
}
