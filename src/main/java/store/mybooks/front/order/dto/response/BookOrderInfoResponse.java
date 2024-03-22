package store.mybooks.front.order.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * packageName    : store.mybooks.front.order.dto.response<br>
 * fileName       : BookOrderInfoResponse<br>
 * author         : minsu11<br>
 * date           : 3/17/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/17/24        minsu11       최초 생성<br>
 */
@Getter
@AllArgsConstructor
@ToString
public class BookOrderInfoResponse {
    private String orderStatus;
    private String number;
    private Integer totalCost;
    private Boolean isCouponUsed;
    private List<BookOrderDetailResponse> orderDetails;
}
