package store.mybooks.front.admin.category.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.category.model.request
 * fileName       : CategoryCreateRequest
 * author         : damho-lee
 * date           : 2/25/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/25/24          damho-lee          최초 생성
 */
@Getter
@AllArgsConstructor
public class CategoryCreateRequest {
    private Integer firstParentCategoryId;
    private Integer secondParentCategoryId;
    private String name;
}
