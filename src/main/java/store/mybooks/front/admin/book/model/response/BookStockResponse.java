package store.mybooks.front.admin.book.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.admin.book.model.response<br>
 * fileName       : BookStockResponse<br>
 * author         : minsu11<br>
 * date           : 3/21/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/21/24        minsu11       최초 생성<br>
 */
@Getter
@AllArgsConstructor
public class BookStockResponse {
    private Long id;
    private Integer stock;
}
