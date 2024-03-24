package store.mybooks.front.elastic.adaptor;

import java.net.URI;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import store.mybooks.front.admin.book.model.response.BookBriefResponse;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.pageable.dto.response.PageResponse;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.elastic.adaptor <br/>
 * fileName       : ElasticAdaptor<br/>
 * author         : newjaehun <br/>
 * date           : 3/20/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/20/24        newjaehun       최초 생성<br/>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ElasticAdaptor {
    private final RestTemplate restTemplate;

    private final GatewayAdaptorProperties gatewayAdaptorProperties;


    public PageResponse<BookBriefResponse> searchPaged(String query, Pageable pageable) {
        Sort.Order order = pageable.getSort().iterator().next();
        URI url = UriComponentsBuilder
                .fromHttpUrl(gatewayAdaptorProperties.getAddress() + "/api/searches")
                .queryParam("query", query)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .queryParam("sort", order.getProperty() + "," + order.getDirection().toString().toLowerCase())
                .encode().build().toUri();

        ResponseEntity<PageResponse<BookBriefResponse>> exchange = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageResponse<BookBriefResponse>>() {
                }
        );
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }
}
