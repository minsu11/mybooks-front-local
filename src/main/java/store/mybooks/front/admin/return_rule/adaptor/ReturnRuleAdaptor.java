package store.mybooks.front.admin.return_rule.adaptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
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

    public List<ReturnRuleResponse> getReturnRuleResponseList() {
        String url = gatewayAdaptorProperties.getAddress() + "/api/return-rules";
        ResponseEntity<List<ReturnRuleResponse>> exchange = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ReturnRuleResponse>>() {
                });
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }
}
