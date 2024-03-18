package store.mybooks.front.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * packageName    : store.mybooks.front.order.dto.response<br>
 * fileName       : BookOrderDetailResponse<br>
 * author         : minsu11<br>
 * date           : 3/18/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/18/24        minsu11       최초 생성<br>
 */
@Getter
@AllArgsConstructor
@ToString
public class BookOrderDetailResponse {
    private String bookName;
    private Integer cost;
    private Boolean isCouponUsed;

}
