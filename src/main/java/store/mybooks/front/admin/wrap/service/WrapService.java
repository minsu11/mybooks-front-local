package store.mybooks.front.admin.wrap.service;

import java.util.List;
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

    /**
     * methodName : getWrapResponse<br>
     * author : minsu11<br>
     * description : 사용 중이고 {@code id}인 포장지 조회.
     * <br> *
     *
     * @param id 조회할 포장지 {@code id}
     * @return wrap response
     */
    public WrapResponse getWrapResponse(Integer id) {
        return wrapAdaptor.getWrapResponse(id);
    }

    public List<WrapResponse> getWrapResponseList() {
        return wrapAdaptor.getWrapResponseList();
    }
}
