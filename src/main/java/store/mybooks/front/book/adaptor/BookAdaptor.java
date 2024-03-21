package store.mybooks.front.book.adaptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.book.model.response.BookBriefResponse;
import store.mybooks.front.admin.book.model.response.BookDetailResponse;
import store.mybooks.front.admin.book.model.response.BookGetResponseForOrder;
import store.mybooks.front.admin.book.model.response.BookStockResponse;
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
@Slf4j
@Component
@RequiredArgsConstructor
public class BookAdaptor {
    private final RestTemplate restTemplate;

    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    private static final String URL = "/api/books";
    private static final String URL_ID = "/api/books/{id}";


    /**
     * methodName : getActiveBriefBook
     * author : newjaehun
     * description : 활성화된(판매중, 재고없음) 간단한 도서 정보.
     *
     * @param id Long
     * @return page
     */
    public Page<BookBriefResponse> getActiveBriefBook(Long id) {
        ResponseEntity<Page<BookBriefResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/active",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Page<BookBriefResponse>>() {
                },
                id);
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }


    /**
     * methodName : getBook
     * author : newjaehun
     * description : 도서 상세정보.
     *
     * @param id Long
     * @return bookDetailResponse
     */
    public BookDetailResponse getBook(Long id) {
        log.info("getBook: {}", id);
        ResponseEntity<BookDetailResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL_ID,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<BookDetailResponse>() {
                },
                id);
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    /**
     * 바로 구매에서 사용될 DTO 데이터.
     *
     * @param bookId the book id
     * @return the book for order
     */
    public BookGetResponseForOrder getBookForOrder(Long bookId) {
        ResponseEntity<BookGetResponseForOrder> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL_ID + "/order",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<BookGetResponseForOrder>() {
                }, bookId
        );
        return Utils.getResponseEntity(exchange, HttpStatus.OK);

    }

    /**
     * {@code id}의 도서 재고 조회.
     *
     * @param bookId the book id
     * @return the book stock response
     */
    public BookStockResponse getBookStockResponse(Long bookId) {
        ResponseEntity<BookStockResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL_ID + "/order/stock",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<BookStockResponse>() {
                }, bookId
        );

        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }


}
