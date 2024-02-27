package store.mybooks.front.admin.category.adaptor;

import java.util.List;
import org.springframework.data.domain.Pageable;
import store.mybooks.front.admin.category.model.request.CategoryCreateRequestForTransmission;
import store.mybooks.front.admin.category.model.request.CategoryModifyRequestForTransmission;
import store.mybooks.front.admin.category.model.response.CategoryGetResponse;
import store.mybooks.front.admin.category.model.response.CategoryGetResponseForUpdate;
import store.mybooks.front.admin.category.model.response.CategoryGetResponseForView;
import store.mybooks.front.pageable.dto.response.PageResponse;

/**
 * packageName    : store.mybooks.front.category.adaptor
 * fileName       : CategoryAdaptor
 * author         : damho-lee
 * date           : 2/24/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/24/24          damho-lee          최초 생성
 */
public interface CategoryAdaptor {
    List<CategoryGetResponse> getHighestCategories();

    List<CategoryGetResponse> getChildCategories(long parentCategoryId);

    void createCategory(CategoryCreateRequestForTransmission categoryCreateRequest);

    PageResponse<CategoryGetResponseForView> getCategories(Pageable pageable);

    void updateCategory(Integer id, CategoryModifyRequestForTransmission categoryModifyRequestForTransmission);

    void deleteCategory(Integer id);

    CategoryGetResponseForUpdate getCategory(Integer id);
}
