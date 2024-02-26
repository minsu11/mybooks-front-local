package store.mybooks.front.admin.category.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : store.mybooks.front.category.model.request
 * fileName       : CategoryCreateRequestForTransmission
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
public class CategoryCreateRequestForTransmission {
    private Integer parentCategoryId;
    private String name;
}
