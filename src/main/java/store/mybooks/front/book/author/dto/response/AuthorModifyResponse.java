package store.mybooks.front.book.author.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * packageName    : store.mybooks.front.book.author.dto.response<br>
 * fileName       : AuthorModifyResponse<br>
 * author         : minsu11<br>
 * date           : 2/25/24<br>
 * description    : 수정한 저자 DTO
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/25/24        minsu11       최초 생성<br>
 */
@Data
@AllArgsConstructor
public class AuthorModifyResponse {
    private String name;
    private String content;
}
