package store.mybooks.front.admin.category.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.category.model.request
 * fileName       : CategoryModifyRequestForTransmission
 * author         : damho-lee
 * date           : 2/26/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/26/24          damho-lee          최초 생성
 */
@Getter
@AllArgsConstructor
public class CategoryModifyRequestForTransmission {
    private Integer parentCategoryId;
    private String name;
}
