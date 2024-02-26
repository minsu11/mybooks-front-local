package store.mybooks.front.admin.author.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.book.author.dto.request<br>
 * fileName       : AuthorRequest<br>
 * author         : minsu11<br>
 * date           : 2/25/24<br>
 * description    : 요청하는 저자 DTO
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/25/24        minsu11       최초 생성<br>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequest {
    private Integer id;
    private String name;
    private String content;
}
