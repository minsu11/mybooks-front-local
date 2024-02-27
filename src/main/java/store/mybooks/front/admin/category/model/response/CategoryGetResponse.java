package store.mybooks.front.admin.category.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.resource.category.dto.response
 * fileName       : CategoryGetResponseDto
 * author         : damho
 * date           : 2/16/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/16/24          damho-lee          최초 생성
 */
@Getter
@NoArgsConstructor
public class CategoryGetResponse {
    private Integer id;
    private CategoryGetResponse parentCategory;
    private String name;
}
