package store.mybooks.front.category.adaptor;

import java.util.List;
import store.mybooks.front.category.model.request.CategoryCreateRequestForTransmission;
import store.mybooks.front.category.model.request.CategoryModifyRequestForTransmission;
import store.mybooks.front.category.model.response.CategoryGetResponse;
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

    PageResponse<CategoryGetResponse> getCategories(int page, int size);

    void updateCategory(Integer id, CategoryModifyRequestForTransmission categoryModifyRequestForTransmission);

    void deleteCategory(Integer id);

    CategoryGetResponse getCategory(Integer id);
}
