package store.mybooks.front.book_order.adaptor;

import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.book_order.model.Response;

/**
 * packageName    : store.mybooks.front.book_order.adaptor
 * fileName       : TestAdaptorImpl
 * author         : minsu11
 * date           : 2/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/19/24        minsu11       최초 생성
 */
@Component

public class TestAdaptorImpl implements TestAdaptor {
    private RestTemplate restTemplate;

    public TestAdaptorImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Response> test() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<List<Response>> requestEntity = new HttpEntity<>(headers);

        //responseEntity
        ResponseEntity<List<Response>> exchange = restTemplate.exchange("http://180.210.83.227:6060" + "/api/orders-statuses",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<Response>>() {
                });
        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
        return exchange.getBody();
    }
}
