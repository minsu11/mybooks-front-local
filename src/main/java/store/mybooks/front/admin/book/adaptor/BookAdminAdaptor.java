package store.mybooks.front.admin.book.adaptor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import store.mybooks.front.admin.book.model.request.BookCreateRequest;
import store.mybooks.front.admin.book.model.request.BookModifyRequest;
import store.mybooks.front.admin.book.model.response.BookBriefResponse;
import store.mybooks.front.admin.book.model.response.BookCreateResponse;
import store.mybooks.front.admin.book.model.response.BookDetailResponse;
import store.mybooks.front.admin.book.model.response.BookModifyResponse;
import store.mybooks.front.admin.book.model.response.BookStatusGetResponse;
import store.mybooks.front.auth.Annotation.RequiredAuthorization;
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
    private final String ADMIN_URL = "/api/admin/books";

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
    @RequiredAuthorization
    public BookCreateResponse createBook(BookCreateRequest bookCreateRequest, MultipartFile thumbnailImage,
                                         List<MultipartFile> contentImages)
            throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("request", bookCreateRequest);
        parts.add("thumbnail", new FileSystemResource(convert(thumbnailImage)));
        for (MultipartFile files : contentImages) {
            parts.add("content", new FileSystemResource(convert(files)));
        }

        HttpEntity<MultiValueMap<String, Object>> requestHttpEntity = new HttpEntity<>(parts, headers);

        ResponseEntity<BookCreateResponse> responseEntity =
                restTemplate.postForEntity(gatewayAdaptorProperties.getAddress() + ADMIN_URL, requestHttpEntity,
                        BookCreateResponse.class);

        ResponseEntity<BookCreateResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL,
                HttpMethod.POST,
                requestHttpEntity,
                new ParameterizedTypeReference<>() {
                }
        );
        return Utils.getResponseEntity(responseEntity, HttpStatus.CREATED);
    }

    private File convert(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fileOutputStream = new FileOutputStream(convFile)) {
            fileOutputStream.write(file.getBytes());
        }
        return convFile;
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
    @RequiredAuthorization
    public BookModifyResponse updateBook(Long bookId, BookModifyRequest modifyRequest) {
        HttpEntity<BookModifyRequest> requestHttpEntity = new HttpEntity<>(modifyRequest, Utils.getHttpHeader());

        ResponseEntity<BookModifyResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + ADMIN_URL + "/{id}",
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
