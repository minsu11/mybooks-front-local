package store.mybooks.front.admin.book.adaptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.book.model.request.BookCreateRequest;
import store.mybooks.front.admin.book.model.request.BookModifyRequest;
import store.mybooks.front.admin.book.model.response.BookBriefResponse;
import store.mybooks.front.admin.book.model.response.BookCreateResponse;
import store.mybooks.front.admin.book.model.response.BookDetailResponse;
import store.mybooks.front.admin.book.model.response.BookModifyResponse;
import store.mybooks.front.admin.book.model.response.BookStatusGetResponse;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.pageable.dto.response.PageResponse;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.admin.book.adaptor <br/>
 * fileName       : BookAdminAdaptor<br/>
 * author         : newjaehun <br/>
 * date           : 2/26/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 2/26/24        newjaehun       최초 생성<br/>
 */
@Component
@RequiredArgsConstructor
public class BookAdminAdaptor {
    private final RestTemplate restTemplate;

    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    private final String URL = "/api/books";

    /**
     * methodName : getPagedBriefBooks
     * author : newjaehun
     * description : 관리자 도서페이지에 보여줄 페이징된 도서정보.
     *
     * @param pageable Pageable
     * @return pageResponse
     */
    public PageResponse<BookBriefResponse> getPagedBriefBooks(Pageable pageable) {
        ResponseEntity<PageResponse<BookBriefResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "?page=" + pageable.getPageNumber() + "&size="
                        + pageable.getPageSize(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    /**
     * methodName : getDetailBook
     * author : newjaehun
     * description : 도서 ID로 도서 상세정보 가져오기.
     *
     * @param bookId Long
     * @return bookDetailResponse BookDetailResponse
     */
    public BookDetailResponse getDetailBook(Long bookId) {
        ResponseEntity<BookDetailResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                bookId);
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    /**
     * methodName : createBook
     * author : newjaehun
     * description : 도서 추가.
     *
     * @param bookCreateRequest BookCreateRequest
     * @return bookCreateResponse
     */
    public BookCreateResponse createBook(BookCreateRequest bookCreateRequest) {
        HttpEntity<BookCreateRequest> requestHttpEntity = new HttpEntity<>(bookCreateRequest, Utils.getHttpHeader());

        ResponseEntity<BookCreateResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL,
                HttpMethod.POST,
                requestHttpEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        return Utils.getResponseEntity(exchange, HttpStatus.CREATED);
    }

    /**
     * methodName : updateBook
     * author : newjaehun
     * description : 도서 수정.
     *
     * @param bookId Long
     * @param modifyRequest BookModifyRequest
     * @return bookModifyResponse
     */
    public BookModifyResponse updateBook(Long bookId, BookModifyRequest modifyRequest) {
        HttpEntity<BookModifyRequest> requestHttpEntity = new HttpEntity<>(modifyRequest, Utils.getHttpHeader());

        ResponseEntity<BookModifyResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/{id}",
                HttpMethod.PUT,
                requestHttpEntity,
                new ParameterizedTypeReference<>() {
                }, bookId);

        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    /**
     * methodName : getBookStatus
     * author : newjaehun
     * description : 전체 도서상태 리스트 반환.
     *
     * @return list
     */
    public List<BookStatusGetResponse> getBookStatus() {
        ResponseEntity<List<BookStatusGetResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + "/api/books-statuses",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }
}
