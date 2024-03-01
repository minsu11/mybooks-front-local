package store.mybooks.front.admin.coupon.model.request;

import java.time.LocalDate;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * packageName    : store.mybooks.front.admin.coupon.model.request
 * fileName       : BookFlatDiscountCouponCreateRequest
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
public class BookFlatDiscountCouponCreateRequest {
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @Positive
    private Long bookId;

    @PositiveOrZero
    private Integer orderMin;

    @Positive
    private Integer discountCost;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent
    private LocalDate endDate;
} // 보내는쪽이나 받는쪽이나 안넘겨주거나 안받는 필드 있어도 정상적으로 작동함 --> 왜?
