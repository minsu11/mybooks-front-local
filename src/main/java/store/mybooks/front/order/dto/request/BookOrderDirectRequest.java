package store.mybooks.front.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.order.dto.dto.request<br>
 * fileName       : BookOrderDirectRequset<br>
 * author         : minsu11<br>
 * date           : 3/6/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/6/24        minsu11       최초 생성<br>
 */
@Getter
@AllArgsConstructor
public class BookOrderDirectRequest {
    private Long id;
    private Integer quantity;
}
