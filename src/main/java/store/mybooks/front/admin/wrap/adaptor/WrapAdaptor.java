package store.mybooks.front.admin.wrap.adaptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.wrap.dto.request.WrapCreateRequest;
import store.mybooks.front.admin.wrap.dto.response.WrapCreateResponse;
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

    /**
     * methodName : getWrap<br>
     * author : minsu11<br>
     * description : 포장지의 목록을 가지고 옴
     * <br> *
     *
     * @return wrap response
     */
    public List<WrapResponse> getWrapList() {
        String url = gatewayAdaptorProperties.getAddress() + "/api/wraps";
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
        String url = gatewayAdaptorProperties.getAddress() + "/api/wraps/page?page=" + pageable.getPageNumber() + "&size=" + pageable.getPageSize();
        ResponseEntity<PageResponse<WrapResponse>> exchange = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageResponse<WrapResponse>>() {
                });
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    public WrapCreateResponse createWrap(WrapCreateRequest request) {
        String url = gatewayAdaptorProperties.getAddress() + "/api/wraps";
        HttpHeaders headers = Utils.getHttpHeader();
        HttpEntity<WrapCreateRequest> responseHttpEntity = new HttpEntity<>(request, headers);

        ResponseEntity<WrapCreateResponse> exchange = restTemplate.exchange(url,
                HttpMethod.POST,
                responseHttpEntity,
                new ParameterizedTypeReference<WrapCreateResponse>() {
                });

        return Utils.getResponseEntity(exchange, HttpStatus.CREATED);
    }

}
