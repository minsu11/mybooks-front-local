package store.mybooks.front.user_coupon.model.response;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.user_coupon.model.response
 * fileName       : UserCouponGetResponse
 * author         : damho-lee
 * date           : 3/6/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/6/24          damho-lee          최초 생성
 */
@Getter
@NoArgsConstructor
public class UserCouponGetResponse {
    private Long userCouponId;
    private String name;
    private String range;
    private String target;
    private Integer orderMin;
    private Integer discountRateOrCost;
    private Integer maxDiscountCost;
    private Boolean isRate;
    private LocalDate startDate;
    private LocalDate endDate;
}
