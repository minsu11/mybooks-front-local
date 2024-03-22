package store.mybooks.front.user_coupon.model.response;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.user_coupon.model.response<br>
 * fileName       : UserCouponResponse<br>
 * author         : minsu11<br>
 * date           : 3/17/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/17/24        minsu11       최초 생성<br>
 */
@Getter
@NoArgsConstructor
public class UserCouponResponse {
    private Long userCouponId;
    private String name;
    private Integer orderMin;
    private Integer discountRate;
    private Integer discountCost;
    private Integer maxDiscountCost;
    private boolean rate;
    private LocalDate startDate;
    private LocalDate endDate;

}
