package store.mybooks.front.booklike.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.book.model.response.BookBriefResponse;
import store.mybooks.front.auth.Annotation.RequiredAuthorization;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.pageable.dto.response.PageResponse;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.booklike.adaptor <br/>
 * fileName       : BookLikeAdaptor<br/>
 * author         : newjaehun <br/>
 * date           : 3/9/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/9/24        newjaehun       최초 생성<br/>
 */
@Component
@RequiredArgsConstructor
public class BookLikeAdaptor {
    private final RestTemplate restTemplate;

    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    private static final String URL = "/api/member/book-likes";

    /**
     * methodName : getPageUserBookLike
     * author : newjaehun
     * description : 회원이 좋아요한 페이징된 도서목록 반환.
     *
     * @param pageable Pageable
     * @return pageResponse response
     */
    @RequiredAuthorization
    public PageResponse<BookBriefResponse> getPageUserBookLike(Pageable pageable) {
        ResponseEntity<PageResponse<BookBriefResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "?page=" + pageable.getPageNumber() + "&size="
                        + pageable.getPageSize(),
                HttpMethod.GET,
                new HttpEntity<>(Utils.getAuthHeader()),
                new ParameterizedTypeReference<>() {
                });
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }


    /**
     * methodName : updateBookLike
     * author : newjaehun
     * description : 사용자가 도서 좋아요 하는 기능.
     *
     * @param bookId Long
     * @return boolean
     */
    @RequiredAuthorization
    public boolean updateBookLike(Long bookId) {
        ResponseEntity<Boolean> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/{bookId}",
                HttpMethod.POST,
                new HttpEntity<>(Utils.getAuthHeader()),
                new ParameterizedTypeReference<>() {
                }, bookId);
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    /**
     * methodName : isUserLikeCheck
     * author : newjaehun
     * description : 도서별 회원의 좋아요 유무.
     *
     * @param bookId Long
     * @return boolean
     */
    @RequiredAuthorization
    public boolean isUserLikeCheck(Long bookId) {
        ResponseEntity<Boolean> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/{bookId}",
                HttpMethod.GET,
                new HttpEntity<>(Utils.getAuthHeader()),
                new ParameterizedTypeReference<>() {
                }, bookId);
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }
}
