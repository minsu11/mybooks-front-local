package store.mybooks.front.book.author.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * packageName    : store.mybooks.front.book.author.dto.request<br>
 * fileName       : AuthorModifyRequest<br>
 * author         : minsu11<br>
 * date           : 2/25/24<br>
 * description    : 수정할 저자의 정보를 가지고 있는 DTO
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/25/24        minsu11       최초 생성<br>
 */
@Data
@AllArgsConstructor
public class AuthorModifyRequest {
    private String name;
    private String content;

}
