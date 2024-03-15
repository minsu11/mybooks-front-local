package store.mybooks.front.category.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.book.model.response.BookBriefResponseIncludePublishDate;
import store.mybooks.front.category.adaptor.CategoryAdaptor;
import store.mybooks.front.category.model.reesponse.CategoryGetResponseForCategoryView;
import store.mybooks.front.category.model.reesponse.CategoryGetResponseForMainView;
import store.mybooks.front.pageable.dto.response.PageResponse;

/**
 * packageName    : store.mybooks.front.category.service
 * fileName       : CategoryService
 * author         : damho-lee
 * date           : 3/13/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/13/24          damho-lee          최초 생성
 */
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryAdaptor categoryAdaptor;

    public List<CategoryGetResponseForMainView> getCategoriesForMainView() {
        return categoryAdaptor.getCategoriesForMainView();
    }

    public CategoryGetResponseForCategoryView getCategoriesForCategoryView(Integer categoryId) {
        return categoryAdaptor.getCategoriesForCategoryView(categoryId);
    }

    public PageResponse<BookBriefResponseIncludePublishDate> getBooksForCategoryView(Integer categoryId,
                                                                                     Pageable pageable) {
        return categoryAdaptor.getBooksForCategoryView(categoryId, pageable);
    }
}
