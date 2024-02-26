package store.mybooks.front.admin.author.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.book.author.dto.request<br>
 * fileName       : AuthorCreateRequest<br>
 * author         : minsu11<br>
 * date           : 2/24/24<br>
 * description    : 저자 등록하는 dto
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/24/24        minsu11       최초 생성<br>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorCreateRequest {
    private String name;
    private String content;
}
