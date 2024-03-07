package store.mybooks.front.admin.point_rule.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.point_rule.dto.request.PointRuleCreateRequest;
import store.mybooks.front.admin.point_rule.dto.request.PointRuleModifyRequest;
import store.mybooks.front.admin.point_rule.dto.response.PointRuleCreateResponse;
import store.mybooks.front.admin.point_rule.dto.response.PointRuleModifyResponse;
import store.mybooks.front.admin.point_rule.dto.response.PointRuleResponse;
import store.mybooks.front.auth.Annotation.RequiredAuthorization;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.pageable.dto.response.PageResponse;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.admin.point.adaptor<br>
 * fileName       : PointAdminAdaptor<br>
 * author         : minsu11<br>
 * date           : 3/7/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/7/24        minsu11       최초 생성<br>
 */
@Component
@RequiredArgsConstructor
public class PointAdminAdaptor {
    private final GatewayAdaptorProperties gatewayAdaptorProperties;
    private final RestTemplate restTemplate;
    private static final String URL = "/api/point-rules";

    /**
     * methodName : getPointRuleResponsePage<br>
     * author : minsu11<br>
     * description : 관리자페이지에서 사용 중인 포인트 규정 목록을 페이징해서 조회.
     * <br> *
     *
     * @param pageable 페이징
     * @return page
     */
    @RequiredAuthorization
    public PageResponse<PointRuleResponse> getPointRuleResponsePage(Pageable pageable) {
        ResponseEntity<PageResponse<PointRuleResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/page?page=" + pageable.getPageNumber() + "&size=" + pageable.getPageSize(),
                HttpMethod.GET,
                new HttpEntity<>(Utils.getAuthHeader()),
                new ParameterizedTypeReference<PageResponse<PointRuleResponse>>() {
                }
        );

        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    /**
     * methodName : createPointRule<br>
     * author : minsu11<br>
     * description : 포인트 규정 등록 요청.
     * <br> *
     *
     * @param request 요청 보낼 데이터
     * @return point rule create response
     */
    @RequiredAuthorization
    public PointRuleCreateResponse createPointRule(PointRuleCreateRequest request) {
        HttpHeaders headers = Utils.getAuthHeader();
        HttpEntity<PointRuleCreateRequest> httpEntity = new HttpEntity<>(request, headers);
        ResponseEntity<PointRuleCreateResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<PointRuleCreateResponse>() {
                }
        );
        return Utils.getResponseEntity(exchange, HttpStatus.CREATED);
    }

    /**
     * methodName : modifyPointRule<br>
     * author : minsu11<br>
     * description : 포인트 규정 수정.
     * <br> *
     *
     * @param request 수정 요청 보낼 데이터
     * @return point rule modify response
     */
    @RequiredAuthorization
    public PointRuleModifyResponse modifyPointRule(PointRuleModifyRequest request, Integer id) {
        HttpHeaders headers = Utils.getAuthHeader();
        HttpEntity<PointRuleModifyRequest> httpEntity = new HttpEntity<>(request, headers);
        ResponseEntity<PointRuleModifyResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/{id}",
                HttpMethod.PUT,
                httpEntity,
                new ParameterizedTypeReference<PointRuleModifyResponse>() {
                }, id
        );
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    @RequiredAuthorization
    public void deletePointRule(Integer id) {
        HttpHeaders headers = Utils.getAuthHeader();
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<Object> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/{id}",
                HttpMethod.DELETE,
                httpEntity,
                new ParameterizedTypeReference<Object>() {
                }, id
        );
        Utils.getResponseEntity(exchange, HttpStatus.NO_CONTENT);
    }

}
