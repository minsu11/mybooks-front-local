package store.mybooks.front.admin.return_rule_name.adaptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.return_rule_name.dto.response.ReturnRuleNameResponse;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.admin.return_rule_name.adaptor<br>
 * fileName       : Adaptor<br>
 * author         : minsu11<br>
 * date           : 3/1/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/1/24        minsu11       최초 생성<br>
 */
@Component
@RequiredArgsConstructor
public class ReturnRuleNameAdaptor {
    private final RestTemplate restTemplate;
    private final GatewayAdaptorProperties gatewayAdaptorProperties;
    private static final String URL = "/api/return-rule-names";

    public List<ReturnRuleNameResponse> getReturnRuleList() {

        ResponseEntity<List<ReturnRuleNameResponse>> exchange = restTemplate.exchange(gatewayAdaptorProperties.getAddress() + URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ReturnRuleNameResponse>>() {
                });
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

}
