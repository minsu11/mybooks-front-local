package store.mybooks.front.order.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : store.mybooks.front.order.dto.request<br>
 * fileName       : BookOrderRequest<br>
 * author         : minsu11<br>
 * date           : 3/16/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/16/24        minsu11       최초 생성<br>
 */
@Getter
@NoArgsConstructor
@Setter
public class BookOrderRequest {
    private List<BookInfoRequest> bookInfoList;
    private OrderInfoRequest orderInfo;
    private OrderUserInfoRequest userInfo;
}
