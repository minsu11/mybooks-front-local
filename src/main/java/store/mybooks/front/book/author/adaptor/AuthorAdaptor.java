package store.mybooks.front.book.author.adaptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.book.author.dto.response.AuthorResponse;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.pageable.dto.response.PageResponse;

/**
 * packageName    : store.mybooks.front.book.author.adaptor<br>
 * fileName       : AuthorAdaptor<br>
 * author         : minsu11<br>
 * date           : 2/23/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/23/24        minsu11       최초 생성<br>
 */
@Component
@RequiredArgsConstructor
public class AuthorAdaptor {
    private final RestTemplate restTemplate;
    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    public PageResponse<AuthorResponse> getAuthor() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<PageResponse<AuthorResponse>> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<PageResponse<AuthorResponse>> exchange = restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/authors",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<PageResponse<AuthorResponse>>() {
                });
        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
        return exchange.getBody();

    }
}
