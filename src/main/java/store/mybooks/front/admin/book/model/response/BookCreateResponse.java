package store.mybooks.front.admin.book.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.admin.book.model.response <br/>
 * fileName       : BookCreateResponse<br/>
 * author         : newjaehun <br/>
 * date           : 2/26/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 2/26/24        newjaehun       최초 생성<br/>
 */
@Getter
@NoArgsConstructor
public class BookCreateResponse {
    private Long id;
    private String name;
}
