package store.mybooks.front.admin.category.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Setter
@NoArgsConstructor
public class TagCreateRequest {
    private String name;
}
