package store.mybooks.front.book.publisher.adaptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.book.publisher.dto.request.PublisherCreateRequest;
import store.mybooks.front.book.publisher.dto.response.PublisherCreateResponse;
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
    private final RestTemplate restTemplate;
    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    /**
     * methodName : getPublisherList<br>
     * author : minsu11<br>
     * description : {@code pagination}을 통해 출판사 정보를 일부분만 가지고 옴
     * <br> *
     *
     * @return page response
     */
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

    /**
     * methodName : registerPublisher<br>
     * author : minsu11<br>
     * description : 등록할 출판사 정보를 백엔드에 보낸 뒤 정상 등록이 된다면 응답 정보를 보냄
     * <br> *
     *
     * @param publisherCreateRequest 등록할 출판사 DTO
     * @return publisher create response
     * @throws RuntimeException {@code http status code created}가 아니면 예외를 던짐
     */
    public PublisherCreateResponse registerPublisher(PublisherCreateRequest publisherCreateRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<PublisherCreateRequest> request = new HttpEntity<>(publisherCreateRequest, headers);
        ResponseEntity<PublisherCreateResponse> exchange = restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/publishers",
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<PublisherCreateResponse>() {
                });
        if (exchange.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException();
        }
        return exchange.getBody();
    }


}
