package store.mybooks.front.book.author.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : store.mybooks.front.book.author.dto.response<br>
 * fileName       : AuthorResponse<br>
 * author         : minsu11<br>
 * date           : 2/24/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/24/24        minsu11       최초 생성<br>
 */
@Getter
@AllArgsConstructor
@Setter
public class AuthorResponse {
    private String name;
    private int id;
    private String content;

    // Getters and setters

}
