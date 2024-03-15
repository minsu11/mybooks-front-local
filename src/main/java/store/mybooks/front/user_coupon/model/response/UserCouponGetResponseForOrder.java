package store.mybooks.front.user_coupon.model.response;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.user_coupon.model.response
 * fileName       : UserCouponGetResponseForOrder
 * author         : damho-lee
 * date           : 3/11/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/11/24          damho-lee          최초 생성
 */
@Getter
@NoArgsConstructor
public class UserCouponGetResponseForOrder {
    private Long userCouponId;
    private String name;
    private Integer orderMin;
    private Integer discountRateOrCost;
    private Integer maxDiscountCost;
    private boolean rate;
    private LocalDate startDate;
    private LocalDate endDate;
}
