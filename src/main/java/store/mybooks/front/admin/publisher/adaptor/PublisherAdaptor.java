package store.mybooks.front.admin.publisher.adaptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.publisher.dto.request.PublisherCreateRequest;
import store.mybooks.front.admin.publisher.dto.request.PublisherModifyRequest;
import store.mybooks.front.admin.publisher.dto.response.PublisherCreateResponse;
import store.mybooks.front.admin.publisher.dto.response.PublisherDeleteResponse;
import store.mybooks.front.admin.publisher.dto.response.PublisherModifyResponse;
import store.mybooks.front.admin.publisher.dto.response.PublisherResponse;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.pageable.dto.response.PageResponse;
import store.mybooks.front.utils.Utils;

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
@Slf4j
@Component
@RequiredArgsConstructor
public class PublisherAdaptor {
    private final RestTemplate restTemplate;
    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    /**
     * methodName : getPublisherList<br>
     * author : minsu11<br>
     * description : {@code pagination}을 통해 출판사 정보를 일부분만 가지고 옴.
     * <br> *
     *
     * @return page response
     */
    public PageResponse<PublisherResponse> getPublisherList(Pageable pageable) {
        String url = gatewayAdaptorProperties.getAddress() + "/api/publishers/page?page=" + pageable.getPageNumber() + "&size==" + pageable.getPageSize();

        HttpEntity<PageResponse<PublisherResponse>> responseHttpEntity = new HttpEntity<>(Utils.getHttpHeader());
        ResponseEntity<PageResponse<PublisherResponse>> exchange = restTemplate.exchange(url,
                HttpMethod.GET,
                responseHttpEntity,
                new ParameterizedTypeReference<PageResponse<PublisherResponse>>() {
                });
        log.info("exchange value: {}", exchange.getBody());

        return Utils.getResponseEntity(exchange, HttpStatus.OK);

    }

    /**
     * methodName : registerPublisher<br>
     * author : minsu11<br>
     * description : 등록할 출판사 정보를 {@code resource}에 보낸 뒤 정상 등록이 된다면 응답 정보를 보냄
     * <br> *
     *
     * @param publisherCreateRequest 등록할 출판사 DTO
     * @return publisher create response
     * @throws RuntimeException {@code http status code created}가 아니면 예외를 던짐
     */
    public PublisherCreateResponse registerPublisher(PublisherCreateRequest publisherCreateRequest) {
        String url = gatewayAdaptorProperties.getAddress() + "/api/publishers";
        HttpHeaders headers = Utils.getHttpHeader();

        HttpEntity<PublisherCreateRequest> request = new HttpEntity<>(publisherCreateRequest, headers);
        ResponseEntity<PublisherCreateResponse> exchange = restTemplate.exchange(url,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<PublisherCreateResponse>() {
                });
        return Utils.getResponseEntity(exchange, HttpStatus.CREATED);


    }

    /**
     * methodName : updatePublisher<br>
     * author : minsu11<br>
     * description : 출판사의 정보 수정.
     * <br> *
     *
     * @param publisherModifyRequest 출판사 수정 정보가 담긴 DTO
     * @param id
     * @return publisher modify response
     */
    public PublisherModifyResponse updatePublisher(PublisherModifyRequest publisherModifyRequest, Integer id) {

        HttpHeaders headers = Utils.getHttpHeader();

        HttpEntity<PublisherModifyRequest> request = new HttpEntity<>(publisherModifyRequest, headers);
        ResponseEntity<PublisherModifyResponse> exchange = restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/publishers/{id}",
                HttpMethod.PUT,
                request,
                new ParameterizedTypeReference<PublisherModifyResponse>() {
                }, id);

        return Utils.getResponseEntity(exchange, HttpStatus.OK);

    }

    /**
     * methodName : deletePublisher<br>
     * author : minsu11<br>
     * description : {@code id}에 맞는 저자의 정보를 삭제
     * <br> *
     *
     * @param id 삭제할 저자의 id
     * @return publisher delete response
     */
    public PublisherDeleteResponse deletePublisher(Integer id) {
        ResponseEntity<PublisherDeleteResponse> exchange = restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/publishers/{id}",
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<PublisherDeleteResponse>() {
                },
                id);
        return Utils.getResponseEntity(exchange, HttpStatus.OK);

    }

}
