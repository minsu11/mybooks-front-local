package store.mybooks.front.review.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.resource.review.dto.response<br>
 * fileName       : ReviewRateResponse<br>
 * author         : masiljangajji<br>
 * date           : 3/19/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/19/24        masiljangajji       최초 생성
 */

@Getter
@NoArgsConstructor
public class ReviewRateResponse {

    private Long totalCount;

    private Double averageRate;


}
