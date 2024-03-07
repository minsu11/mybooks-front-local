package store.mybooks.front.admin.point_rule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.point_rule.adaptor.PointAdminAdaptor;
import store.mybooks.front.admin.point_rule.dto.response.PointRuleResponse;
import store.mybooks.front.pageable.dto.response.PageResponse;

/**
 * packageName    : store.mybooks.front.admin.point_rule.service<br>
 * fileName       : PointRuleService<br>
 * author         : minsu11<br>
 * date           : 3/7/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/7/24        minsu11       최초 생성<br>
 */
@Service
@RequiredArgsConstructor
public class PointRuleService {
    private final PointAdminAdaptor pointAdminAdaptor;

    /**
     * methodName : getPointResponsePage<br>
     * author : minsu11<br>
     * description : 페이징된 포인트 규정 조회.
     * <br> *
     *
     * @param pageable 페이징
     * @return page response
     */
    public PageResponse<PointRuleResponse> getPointResponsePage(Pageable pageable) {
        return pointAdminAdaptor.getPointRuleResponsePage(pageable);
    }
}
