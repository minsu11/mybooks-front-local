package store.mybooks.front.admin.wrap.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.wrap.adaptor.WrapAdaptor;
import store.mybooks.front.admin.wrap.dto.request.WrapCreateRequest;
import store.mybooks.front.admin.wrap.dto.request.WrapModifyRequest;
import store.mybooks.front.admin.wrap.dto.response.WrapResponse;
import store.mybooks.front.global.exception.ManageFailedException;
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

    /**
     * methodName : getWrapResponse<br>
     * author : minsu11<br>
     * description : 포장지의 전체 목록.
     * <br> *
     *
     * @return list
     */
    public List<WrapResponse> getWrapResponse() {
        return wrapAdaptor.getWrapList();
    }

    /**
     * methodName : getWrapPage<br>
     * author : minsu11<br>
     * description : 페이징 처리된 포장지 목록.
     * <br> *
     *
     * @param pageable 페이징 정보
     * @return page response
     */
    public PageResponse<WrapResponse> getWrapPage(Pageable pageable) {
        return wrapAdaptor.getWrapPage(pageable);
    }

    /**
     * methodName : createWrap<br>
     * author : minsu11<br>
     * description : 포장지 등록. 등록 실패 시 {@code redirectUrl} 주소로 {@code redirects}.
     * <br> *
     *
     * @param request     등록할 포장지
     * @param redirectUrl 등록 실패 시 {@code redirect} 주소
     */
    public void createWrap(WrapCreateRequest request, String redirectUrl) {
        try {
            wrapAdaptor.createWrap(request);
        } catch (RuntimeException e) {
            throw new ManageFailedException("등록 실패", redirectUrl);

        }
    }

    /**
     * methodName : updateWrap<br>
     * author : minsu11<br>
     * description : 포장지 수정.
     * <br> *
     *
     * @param request     수정할 포장지 정보
     * @param redirectUrl 수정 실패 시 {@code redirect} 주소
     */
    public void updateWrap(WrapModifyRequest request, String redirectUrl) {
        try {
            wrapAdaptor.updateWrap(request, request.getId());
        } catch (RuntimeException e) {
            throw new ManageFailedException("수정 실패", redirectUrl);
        }
    }


}
