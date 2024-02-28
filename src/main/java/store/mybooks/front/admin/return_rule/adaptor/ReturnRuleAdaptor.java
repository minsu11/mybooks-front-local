package store.mybooks.front.admin.return_rule.adaptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.return_rule.dto.request.ReturnRuleCreateRequest;
import store.mybooks.front.admin.return_rule.dto.response.ReturnRuleCreateResponse;
import store.mybooks.front.admin.return_rule.dto.response.ReturnRuleResponse;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.admin.return_rule.adaptor<br>
 * fileName       : ReturnRuleAdaptor<br>
 * author         : minsu11<br>
 * date           : 2/28/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/28/24        minsu11       최초 생성<br>
 */
@Component
@RequiredArgsConstructor
public class ReturnRuleAdaptor {
    private final RestTemplate restTemplate;
    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    /**
     * methodName : getReturnRuleResponseList<br>
     * author : minsu11<br>
     * description : 반품 규정 전체 목록 조회
     * <br> *
     *
     * @return list
     */
    public List<ReturnRuleResponse> getReturnRuleResponseList() {
        String url = gatewayAdaptorProperties.getAddress() + "/api/return-rules";
        ResponseEntity<List<ReturnRuleResponse>> exchange = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ReturnRuleResponse>>() {
                });
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    /**
     * methodName : createReturnRule<br>
     * author : minsu11<br>
     * description : 반품 규정 등록
     * <br> *
     *
     * @param request
     * @return return rule create response
     */
    public ReturnRuleCreateResponse createReturnRule(ReturnRuleCreateRequest request) {
        String url = gatewayAdaptorProperties.getAddress() + "/api/return-rules";
        HttpHeaders headers = Utils.getHttpHeader();
        HttpEntity<ReturnRuleCreateRequest> requestHttpEntity = new HttpEntity<>(request, headers);
        ResponseEntity<ReturnRuleCreateResponse> exchange = restTemplate.exchange(url,
                HttpMethod.POST,
                requestHttpEntity,
                new ParameterizedTypeReference<ReturnRuleCreateResponse>() {
                });
        return Utils.getResponseEntity(exchange, HttpStatus.CREATED);
    }
}
