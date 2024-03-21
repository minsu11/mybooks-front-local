package store.mybooks.front.order.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName    : store.mybooks.front.order.dto.request<br>
 * fileName       : BookInfoRequest<br>
 * author         : minsu11<br>
 * date           : 3/16/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/16/24        minsu11       최초 생성<br>
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BookInfoRequest {
    private Long bookId;
    private Integer saleCost;
    private Integer bookCost;
    private Integer amount;
    private Integer selectWrapId;
    private Long selectCouponId;

}
