package store.mybooks.front.pointhistory.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.auth.Annotation.RequiredAuthorization;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.pageable.dto.response.PageResponse;
import store.mybooks.front.pointhistory.model.PointHistoryGetResponse;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.pointhistory.adaptor
 * fileName       : PointHistoryAdaptor
 * author         : damho-lee
 * date           : 3/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/19/24          damho-lee          최초 생성
 */
@Component
@RequiredArgsConstructor
public class PointHistoryAdaptor {
    private final RestTemplate restTemplate;

    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    private static final String URL = "/api/point-histories";
    private static final String URL_MEMBER = "/api/member/point-histories";


    /**
     * methodName : getPointHistories <br>
     * author : damho-lee <br>
     * description : 포인트 사용 내역 패이지 조회.<br>
     *
     * @param pageable Pageable
     * @return PageResponse
     */
    @RequiredAuthorization
    public PageResponse<PointHistoryGetResponse> getPointHistories(Pageable pageable) {
        HttpHeaders headers = Utils.getAuthHeader();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<PageResponse<PointHistoryGetResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL_MEMBER
                        + "/history?page=" + pageable.getPageNumber() + "&size=" + pageable.getPageSize(),
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }
}
