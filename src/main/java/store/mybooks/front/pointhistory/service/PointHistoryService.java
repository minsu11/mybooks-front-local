package store.mybooks.front.pointhistory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import store.mybooks.front.pageable.dto.response.PageResponse;
import store.mybooks.front.pointhistory.adaptor.PointHistoryAdaptor;
import store.mybooks.front.pointhistory.model.PointHistoryGetResponse;

/**
 * packageName    : store.mybooks.front.pointhistory.service
 * fileName       : PointHistoryService
 * author         : damho-lee
 * date           : 3/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/19/24          damho-lee          최초 생성
 */
@Service
@RequiredArgsConstructor
public class PointHistoryService {
    private final PointHistoryAdaptor pointHistoryAdaptor;

    public PageResponse<PointHistoryGetResponse> getPointHistories(Pageable pageable) {
        return pointHistoryAdaptor.getPointHistories(pageable);
    }
}
