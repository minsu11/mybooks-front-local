package store.mybooks.front.admin.wrap.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.wrap.adaptor.WrapAdaptor;
import store.mybooks.front.admin.wrap.dto.response.WrapResponse;
import store.mybooks.front.pageable.dto.response.PageResponse;

/**
 * packageName    : store.mybooks.front.admin.wrap.service<br>
 * fileName       : WrapService<br>
 * author         : minsu11<br>
 * date           : 2/29/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/29/24        minsu11       최초 생성<br>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WrapService {
    private final WrapAdaptor wrapAdaptor;

    public List<WrapResponse> getWrapResponse() {
        return wrapAdaptor.getWrapList();
    }

    public PageResponse<WrapResponse> getWrapPage(Pageable pageable) {
        return wrapAdaptor.getWrapPage(pageable);
    }

}
