package store.mybooks.front.admin.category.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.category.adaptor.CategoryAdaptor;
import store.mybooks.front.admin.category.model.request.CategoryCreateRequest;
import store.mybooks.front.admin.category.model.request.CategoryCreateRequestForTransmission;
import store.mybooks.front.admin.category.model.request.CategoryModifyRequest;
import store.mybooks.front.admin.category.model.request.CategoryModifyRequestForTransmission;
import store.mybooks.front.admin.category.model.response.CategoryGetResponse;
import store.mybooks.front.admin.category.model.response.CategoryGetResponseForUpdate;
import store.mybooks.front.admin.category.model.response.CategoryGetResponseForView;
import store.mybooks.front.admin.category.model.response.CategoryIdAndName;
import store.mybooks.front.pageable.dto.response.PageResponse;

/**
 * packageName    : store.mybooks.front.category.service
 * fileName       : CategorySservice
 * author         : damho-lee
 * date           : 2/24/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/24/24          damho-lee          최초 생성
 */
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryAdaptor categoryAdaptor;

    /**
     * methodName : getCategories <br>
     * author : damho-lee <br>
     * description : page, size 에 맞는 카테고리 리턴.<br>
     *
     * @param page int
     * @param size int
     * @return pageResponse
     */
    public PageResponse<CategoryGetResponseForView> getCategories(int page, int size) {
        PageResponse<CategoryGetResponse> pageResponse = categoryAdaptor.getCategories(page, size);
        List<CategoryGetResponseForView> categoryGetResponseForViewList = new ArrayList<>();
        for (CategoryGetResponse categoryGetResponse : pageResponse.getContent()) {
            CategoryGetResponse firstCategory = categoryGetResponse.getParentCategory();
            CategoryGetResponse secondCategory = null;

            if (categoryGetResponse.getParentCategory() != null && firstCategory.getParentCategory() != null) {
                secondCategory = firstCategory;
                firstCategory = firstCategory.getParentCategory();
            }

            String firstCategoryName = firstCategory == null ? "" : firstCategory.getName();
            String secondCategoryName = secondCategory == null ? "" : secondCategory.getName();

            String parentCategoryName = firstCategoryName;
            if (!secondCategoryName.isEmpty()) {
                parentCategoryName = parentCategoryName.concat("/").concat(secondCategoryName);
            }

            categoryGetResponseForViewList.add(new CategoryGetResponseForView(
                    categoryGetResponse.getId(),
                    categoryGetResponse.getName(),
                    parentCategoryName
            ));
        }

        return new PageResponse<>(
                categoryGetResponseForViewList,
                pageResponse.getPageable(),
                pageResponse.isLast(),
                pageResponse.getTotalPages(),
                pageResponse.getTotalElements(),
                pageResponse.getSize(),
                pageResponse.getNumber(),
                pageResponse.isFirst(),
                pageResponse.getSort(),
                pageResponse.getNumberOfElements(),
                pageResponse.isEmpty()
        );
    }

    /**
     * methodName : getHighestCategories <br>
     * author : damho-lee <br>
     * description : 최상위 카테고리들의 id 와 이름만 가져온다.<br>
     *
     * @return list
     */
    public List<CategoryIdAndName> getHighestCategories() {
        return categoryAdaptor.getHighestCategories()
                .stream()
                .map(categoryGetResponse -> new CategoryIdAndName(categoryGetResponse.getId(),
                        categoryGetResponse.getName()))
                .collect(Collectors.toList());
    }

    /**
     * methodName : getChildCategories <br>
     * author : damho-lee <br>
     * description : category-register.html 에서 1단계 카테고리를 선택했을 때 2단계 카테고리 리스트를 가져오기 위한 메서드.<br>
     *
     * @param parentCategoryId ParentCategoryId
     * @return list
     */
    public List<CategoryIdAndName> getChildCategories(Integer parentCategoryId) {
        return categoryAdaptor.getChildCategories(parentCategoryId)
                .stream()
                .map(categoryGetResponse -> new CategoryIdAndName(categoryGetResponse.getId(),
                        categoryGetResponse.getName()))
                .collect(Collectors.toList());
    }

    /**
     * methodName : createCategory <br>
     * author : damho-lee <br>
     * description : 카테고리 생성 메서드.<br>
     *
     * @param categoryCreateRequest CategoryCreateRequest
     */
    public void createCategory(CategoryCreateRequest categoryCreateRequest) {
        Integer parentCategoryId = categoryCreateRequest.getFirstParentCategoryId();
        if (categoryCreateRequest.getSecondParentCategoryId() != null) {
            parentCategoryId = categoryCreateRequest.getSecondParentCategoryId();
        }

        categoryAdaptor.createCategory(new CategoryCreateRequestForTransmission(
                parentCategoryId,
                categoryCreateRequest.getName()
        ));
    }

    /**
     * methodName : updateCategory <br>
     * author : damho-lee <br>
     * description : 카테고리 수정 메서드.<br>
     *
     * @param categoryModifyRequest CategoryModifyRequest
     */
    public void updateCategory(CategoryModifyRequest categoryModifyRequest) {
        Integer parentCategoryId = categoryModifyRequest.getFirstParentCategoryId();
        if (categoryModifyRequest.getSecondParentCategoryId() != null) {
            parentCategoryId = categoryModifyRequest.getSecondParentCategoryId();
        }

        categoryAdaptor.updateCategory(categoryModifyRequest.getId(), new CategoryModifyRequestForTransmission(
                parentCategoryId,
                categoryModifyRequest.getName()
        ));
    }

    /**
     * methodName : deleteCategory <br>
     * author : damho-lee <br>
     * description : 카테고리 삭제 메서드.<br>
     *
     * @param id Integer
     */
    public void deleteCategory(Integer id) {
        categoryAdaptor.deleteCategory(id);
    }

    /**
     * methodName : getCategory <br>
     * author : damho-lee <br>
     * description : id 로 수정하려는 Category 를 찾아서 리턴. 1단계, 2단계 카테고리를 찾음.<br>
     *
     * @param id Integer
     * @return CategoryGetResponseForUpdate get response for update
     */
    public CategoryGetResponseForUpdate getCategory(Integer id) {
        CategoryGetResponse categoryGetResponse = categoryAdaptor.getCategory(id);
        CategoryGetResponse levelOneCategory = categoryGetResponse.getParentCategory();
        CategoryGetResponse levelTwoCategory = null;
        if (levelOneCategory != null && levelOneCategory.getParentCategory() != null) {
            levelTwoCategory = levelOneCategory;
            levelOneCategory = levelOneCategory.getParentCategory();
        }

        return new CategoryGetResponseForUpdate(categoryGetResponse, levelOneCategory, levelTwoCategory);
    }
}
