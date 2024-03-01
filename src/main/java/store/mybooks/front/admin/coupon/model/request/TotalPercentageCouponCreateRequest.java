package store.mybooks.front.admin.coupon.model.request;

import java.time.LocalDate;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * packageName    : store.mybooks.front.admin.coupon.model.request
 * fileName       : TotalPercentageCoupon
 * author         : damho-lee
 * date           : 3/1/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/1/24          damho-lee          최초 생성
 */
@Getter
@AllArgsConstructor
public class TotalPercentageCouponCreateRequest {
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @PositiveOrZero
    private Integer orderMin;

    @Positive
    private Integer maxDiscountCost;

    @Min(1)
    @Max(99)
    private Integer discountRate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent
    private LocalDate endDate;
}