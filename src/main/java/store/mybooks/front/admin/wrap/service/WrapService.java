package store.mybooks.front.admin.wrap.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.wrap.adaptor.WrapAdaptor;
import store.mybooks.front.admin.wrap.dto.response.WrapResponse;

/**
 * packageName    : store.mybooks.front.admin.wrap.service<br>
 * fileName       : WrapService<br>
 * author         : minsu11<br>
 * date           : 2/28/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/28/24        minsu11       최초 생성<br>
 */
@Service
@RequiredArgsConstructor
public class WrapService {
    private final WrapAdaptor wrapAdaptor;

    public WrapResponse getWrapResponse(Integer id) {
        return wrapAdaptor.getWrapResponse(id);
    }
}
