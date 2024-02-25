package store.mybooks.front.book.publisher.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.book.publisher.dto.response<br>
 * fileName       : PublisherResponse<br>
 * author         : minsu11<br>
 * date           : 2/25/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/25/24        minsu11       최초 생성<br>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherResponse {
    private String name;
    private Integer id;
}
