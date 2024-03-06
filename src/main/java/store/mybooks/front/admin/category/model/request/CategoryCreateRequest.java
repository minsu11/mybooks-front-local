package store.mybooks.front.admin.category.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
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
    @Positive
    private Integer firstParentCategoryId;
    @Positive
    private Integer secondParentCategoryId;
    @NotBlank
    @Size(min = 1, max = 20)
    private String name;
}
