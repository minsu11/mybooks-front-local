package store.mybooks.front.book.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.book.model.response.BookDetailResponse;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.book.adaptor <br/>
 * fileName       : BookAdaptor<br/>
 * author         : newjaehun <br/>
 * date           : 3/1/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/1/24        newjaehun       최초 생성<br/>
 */
@Component
@RequiredArgsConstructor
public class BookAdaptor {
    private final RestTemplate restTemplate;

    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    private static final String URL = "/api/books";

    public BookDetailResponse getBook(Long id) {
        ResponseEntity<BookDetailResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<BookDetailResponse>() {
                },
                id);
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }
}
