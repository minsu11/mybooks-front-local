package store.mybooks.front.admin.wrap.adaptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.wrap.dto.request.WrapCreateRequest;
import store.mybooks.front.admin.wrap.dto.request.WrapModifyRequest;
import store.mybooks.front.admin.wrap.dto.response.WrapCreateResponse;
import store.mybooks.front.admin.wrap.dto.response.WrapModifyResponse;
import store.mybooks.front.admin.wrap.dto.response.WrapResponse;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.pageable.dto.response.PageResponse;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.admin.wrap.adaptor<br>
 * fileName       : WrapAdaptor<br>
 * author         : minsu11<br>
 * date           : 2/29/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/29/24        minsu11       최초 생성<br>
 */
@Component
@RequiredArgsConstructor
public class WrapAdaptor {
    private final RestTemplate restTemplate;
    private final GatewayAdaptorProperties gatewayAdaptorProperties;
    private static final String URL = "/api/wraps";

    /**
     * methodName : getWrap<br>
     * author : minsu11<br>
     * description : 포장지의 목록을 가지고 옴
     * <br> *
     *
     * @return wrap response
     */
    public List<WrapResponse> getWrapList() {
        String url = gatewayAdaptorProperties.getAddress() + URL;
        ResponseEntity<List<WrapResponse>> exchange = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    /**
     * methodName : getWrapPage<br>
     * author : minsu11<br>
     * description : 페이징 처리된 포장지 목록을 가지고 옴
     * <br> *
     *
     * @return page response
     */
    public PageResponse<WrapResponse> getWrapPage(Pageable pageable) {
        String url = gatewayAdaptorProperties.getAddress() + URL + "/page?page=" + pageable.getPageNumber() + "&size=" + pageable.getPageSize();
        ResponseEntity<PageResponse<WrapResponse>> exchange = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageResponse<WrapResponse>>() {
                });
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    /**
     * methodName : createWrap<br>
     * author : minsu11<br>
     * description : 포장지 등록 api
     * <br> *
     *
     * @param request
     */
    public void createWrap(WrapCreateRequest request) {
        String url = gatewayAdaptorProperties.getAddress() + URL;
        HttpHeaders headers = Utils.getHttpHeader();
        HttpEntity<WrapCreateRequest> responseHttpEntity = new HttpEntity<>(request, headers);

        ResponseEntity<WrapCreateResponse> exchange = restTemplate.exchange(url,
                HttpMethod.POST,
                responseHttpEntity,
                new ParameterizedTypeReference<WrapCreateResponse>() {
                });
        Utils.getResponseEntity(exchange, HttpStatus.CREATED);
    }

    /**
     * methodName : updateWrap<br>
     * author : minsu11<br>
     * description : 포장지 수정 api
     * <br> *
     *
     * @param wrapModifyRequest 수정할 포장지
     * @param id                수정할 포장지 아이디
     */
    public void updateWrap(WrapModifyRequest wrapModifyRequest, Integer id) {
        String url = gatewayAdaptorProperties.getAddress() + URL + "/{id}";
        HttpHeaders headers = Utils.getHttpHeader();
        HttpEntity<WrapModifyRequest> requestHttpEntity = new HttpEntity<>(wrapModifyRequest, headers);
        ResponseEntity<WrapModifyResponse> responseEntity = restTemplate.exchange(url,
                HttpMethod.PUT,
                requestHttpEntity,
                new ParameterizedTypeReference<WrapModifyResponse>() {
                }, id);
        Utils.getResponseEntity(responseEntity, HttpStatus.OK);
    }

}
