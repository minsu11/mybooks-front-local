package store.mybooks.front.pointhistory.model;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.pointhistory.model
 * fileName       : PointHistoryGetResponse
 * author         : damho-lee
 * date           : 3/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/19/24          damho-lee          최초 생성
 */
@Getter
@NoArgsConstructor
public class PointHistoryGetResponse {
    private String pointRuleName;
    private Integer statusCost;
    private LocalDate createdDate;
}
