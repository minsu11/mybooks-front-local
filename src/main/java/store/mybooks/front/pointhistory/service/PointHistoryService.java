package store.mybooks.front.pointhistory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import store.mybooks.front.pointhistory.adaptor.PointHistoryAdaptor;
import store.mybooks.front.pointhistory.model.PointResponseForUser;

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

    /**
     * methodName : getPointHistories <br>
     * author : damho-lee <br>
     * description : 포인트 내역 페이지 조회.<br>
     *
     * @param pageable Pageable
     * @return PageResponse
     */
    public PointResponseForUser getPointHistories(Pageable pageable) {
        return pointHistoryAdaptor.getPointHistories(pageable);
    }
}
