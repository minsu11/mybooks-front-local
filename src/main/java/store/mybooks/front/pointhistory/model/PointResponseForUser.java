package store.mybooks.front.pointhistory.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import store.mybooks.front.pageable.dto.response.PageResponse;

/**
 * packageName    : store.mybooks.front.pointhistory.model
 * fileName       : PointHistoryInfoResponse
 * author         : damho-lee
 * date           : 3/25/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/25/24          damho-lee          최초 생성
 */
@Getter
@NoArgsConstructor
public class PointResponseForUser {
    private Integer remainPoint;
    private PageResponse<PointHistoryResponse> pointHistoryResponsePage;
}
