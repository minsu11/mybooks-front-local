package store.mybooks.front.book.author.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.book.author.dto.response<br>
 * fileName       : AuthorCreateResponse<br>
 * author         : minsu11<br>
 * date           : 2/24/24<br>
 * description    : 저자 등록시 반환 되는 데이터
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/24/24        minsu11       최초 생성<br>
 */
@Getter
@AllArgsConstructor
public class AuthorCreateResponse {
    private String name;
    private String content;
}
