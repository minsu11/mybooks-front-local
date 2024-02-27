package store.mybooks.front.admin.category.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.category.model
 * fileName       : CategoryIdAndName
 * author         : damho-lee
 * date           : 2/24/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/24/24          damho-lee          최초 생성
 */
@Getter
@AllArgsConstructor
public class CategoryIdAndName {
    private Integer id;
    private String name;
}
