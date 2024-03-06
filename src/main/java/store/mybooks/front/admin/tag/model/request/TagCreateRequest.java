package store.mybooks.front.admin.tag.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.category.model.request
 * fileName       : TagCreateRequest
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
public class TagCreateRequest {
    @NotBlank
    @Size(min = 1, max = 20)
    private String name;
}
