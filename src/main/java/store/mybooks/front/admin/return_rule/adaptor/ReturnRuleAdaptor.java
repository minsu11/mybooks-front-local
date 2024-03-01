package store.mybooks.front.admin.return_rule.adaptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.return_rule.dto.request.ReturnRuleCreateRequest;
import store.mybooks.front.admin.return_rule.dto.request.ReturnRuleModifyRequest;
import store.mybooks.front.admin.return_rule.dto.response.ReturnRuleCreateResponse;
import store.mybooks.front.admin.return_rule.dto.response.ReturnRuleModifyResponse;
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
@Slf4j
@Component
@RequiredArgsConstructor
public class ReturnRuleAdaptor {
    private final RestTemplate restTemplate;
    private final GatewayAdaptorProperties gatewayAdaptorProperties;
    private static final String URL = "/api/return-rules";


    /**
     * methodName : getReturnRuleResponseList<br>
     * author : minsu11<br>
     * description : 반품 규정 전체 목록 조회
     * <br> *
     *
     * @return list
     */
    public List<ReturnRuleResponse> getReturnRuleResponseList() {
        log.info("주소 값: {}", gatewayAdaptorProperties.getAddress());

        ResponseEntity<List<ReturnRuleResponse>> exchange = restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL,
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

        HttpEntity<ReturnRuleCreateRequest> requestHttpEntity = new HttpEntity<>(request, Utils.getHttpHeader());
        ResponseEntity<ReturnRuleCreateResponse> exchange = restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL,
                HttpMethod.POST,
                requestHttpEntity,
                new ParameterizedTypeReference<ReturnRuleCreateResponse>() {
                });
        return exchange.getBody();
    }

    /**
     * methodName : modifyReturnRule<br>
     * author : minsu11<br>
     * description : 반품 규정 수정
     * <br> *
     *
     * @param request 반품 규정 수정 데이터
     * @param id      수정할 반품 규정 아이디
     * @return return rule modify response
     */
    public ReturnRuleModifyResponse modifyReturnRule(ReturnRuleModifyRequest request, Integer id) {
        HttpEntity<ReturnRuleModifyRequest> requestHttpEntity = new HttpEntity<>(request, Utils.getHttpHeader());
        ResponseEntity<ReturnRuleModifyResponse> exchange = restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL + "/{id}",
                HttpMethod.PUT,
                requestHttpEntity,
                new ParameterizedTypeReference<ReturnRuleModifyResponse>() {
                }, id);
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    public void deleteReturnRule(Integer id) {
        ResponseEntity<Object> exchange = restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL + "/{id}",
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<>() {
                }, id);
        Utils.getResponseEntity(exchange, HttpStatus.NO_CONTENT);
    }

}
