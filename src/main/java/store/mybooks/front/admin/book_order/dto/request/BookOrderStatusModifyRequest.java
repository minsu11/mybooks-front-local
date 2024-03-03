package store.mybooks.front.admin.book_order.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.admin.book_order.dto.request<br>
 * fileName       : BookOrderStatusModifyRequest<br>
 * author         : minsu11<br>
 * date           : 3/3/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/3/24        minsu11       최초 생성<br>
 */
@Getter
@NoArgsConstructor
public class BookOrderStatusModifyRequest {
    private Long id;
    private String statusId;
}
