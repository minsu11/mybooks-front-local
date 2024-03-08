package store.mybooks.front.admin.author.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.admin.author.dto.response <br/>
 * fileName       : AuthorGetResponse<br/>
 * author         : newjaehun <br/>
 * date           : 3/3/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/3/24        newjaehun       최초 생성<br/>
 */
@Getter
@NoArgsConstructor
public class AuthorGetResponse {
    private Integer id;

    private String name;

    private String content;
}
