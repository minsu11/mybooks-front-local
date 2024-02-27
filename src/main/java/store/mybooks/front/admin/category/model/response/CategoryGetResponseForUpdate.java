package store.mybooks.front.admin.category.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : store.mybooks.front.category.model.response
 * fileName       : CategoryGetResponseForUpdate
 * author         : damho-lee
 * date           : 2/26/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/26/24          damho-lee          최초 생성
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryGetResponseForUpdate {
    private CategoryGetResponse targetCategory;
    private String levelOneCategoryName;
    private String levelTwoCategoryName;
}
