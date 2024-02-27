package store.mybooks.front.admin.tag.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.category.model.response
 * fileName       : TagGetResponse
 * author         : damho-lee
 * date           : 2/26/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/26/24          damho-lee          최초 생성
 */
@Getter
@NoArgsConstructor
public class TagGetResponse {
    private Integer id;
    private String name;
}
