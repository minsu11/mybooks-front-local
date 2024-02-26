package store.mybooks.front.admin.author.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
public class AuthorModifyResponse {
    private String changedName;
    private String changedContent;
}
