package store.mybooks.front.book.publisher.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.book.publisher.dto.response.PublisherResponse;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.pageable.dto.response.PageResponse;

/**
 * packageName    : store.mybooks.front.book.publisher.adaptor<br>
 * fileName       : PublisherAdaptor<br>
 * author         : minsu11<br>
 * date           : 2/25/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/25/24        minsu11       최초 생성<br>
 */
@Component
@RequiredArgsConstructor
public class PublisherAdaptor {
    private RestTemplate restTemplate;
    private GatewayAdaptorProperties gatewayAdaptorProperties;

    public PageResponse<PublisherResponse> getPublisherList() {
        ResponseEntity<PageResponse<PublisherResponse>> exchange = restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/publishers",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageResponse<PublisherResponse>>() {
                });
        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
        return exchange.getBody();
    }
}
