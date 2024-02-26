package store.mybooks.front.admin.publisher.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.book.publisher.dto.response<br>
 * fileName       : PublisherCreateResponse<br>
 * author         : minsu11<br>
 * date           : 2/26/24<br>
 * description    : 출판사 등록한 후 DTO
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/26/24        minsu11       최초 생성<br>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherCreateResponse {
    private String name;

}
