package store.mybooks.front.admin.book_order.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : store.mybooks.front.admin.book_order.dto.response<br>
 * fileName       : BookOrderStatusModifyResponse<br>
 * author         : minsu11<br>
 * date           : 3/3/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/3/24        minsu11       최초 생성<br>
 */
@Getter
@Setter
public class BookOrderStatusModifyResponse {
    private Long id;
    private String statusId;
}
