package store.mybooks.front.admin.author.adaptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.author.dto.request.AuthorCreateRequest;
import store.mybooks.front.admin.author.dto.request.AuthorModifyRequest;
import store.mybooks.front.admin.author.dto.response.AuthorCreateResponse;
import store.mybooks.front.admin.author.dto.response.AuthorDeleteResponse;
import store.mybooks.front.admin.author.dto.response.AuthorModifyResponse;
import store.mybooks.front.admin.author.dto.response.AuthorResponse;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.pageable.dto.response.PageResponse;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.book.author.adaptor<br>
 * fileName       : AuthorAdaptor<br>
 * author         : minsu11<br>
 * date           : 2/23/24<br>
 * description    : gateway 서버와 연결되어 요청과 응답을 해주는 adaptor
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

    private static final String URL = "/api/authors";
    private static final String URL_ID = "/api/authors/{id}";


    /**
     * methodName : getAuthor<br>
     * author : minsu11<br>
     * description : 요청한 {@code id}를 통해 저자의 정보를 조회.
     * <br> *
     *
     * @param id 조회할 저자의 {@code id}
     * @return author response
     * @throws RuntimeException {@code Http status code ok}가 나오지 않는 경우
     */
    public AuthorResponse getAuthor(Integer id) {
        ResponseEntity<AuthorResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL_ID,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<AuthorResponse>() {
                },
                id);
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }


    /**
     * methodName : getAllAuthors
     * author : newjaehun
     * description : 전체 저자 리스트 조회.
     *
     * @return list
     */
    public List<AuthorResponse> getAllAuthors() {
        ResponseEntity<List<AuthorResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AuthorResponse>>() {
                });
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    /**
     * methodName : getAuthors<br>
     * author : minsu11<br>
     * description : 모든 저자의 정보를 조회.
     * <br> *
     *
     * @return page response
     */
    public PageResponse<AuthorResponse> getPagedAuthors(Pageable pageable) {
        ResponseEntity<PageResponse<AuthorResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/page?page=" + pageable.getPageNumber()
                        + "&size=" + pageable.getPageSize(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageResponse<AuthorResponse>>() {
                });
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    /**
     * methodName : createAuthor<br>
     * author : minsu11<br>
     * description : {@code AuthorCreateRequest}를 {@code gateway}로 요청 보내
     * 저자를 등록한 후 {@code AuthorCreateResponse}로 응답 받음.
     * <br>
     *
     * @param authorCreateRequest 등록할 저자
     * @return authorCreateResponse 등록하고 응답 받은 데이터
     */
    public AuthorCreateResponse createAuthor(AuthorCreateRequest authorCreateRequest) {
        HttpEntity<AuthorCreateRequest> requestHttpEntity = new HttpEntity<>(authorCreateRequest, Utils.getHttpHeader());
        ResponseEntity<AuthorCreateResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL,
                HttpMethod.PUT,
                requestHttpEntity,
                new ParameterizedTypeReference<AuthorCreateResponse>() {
                });
        return Utils.getResponseEntity(exchange, HttpStatus.CREATED);
    }

    /**
     * methodName : modifyResponse<br>
     * author : minsu11<br>
     * description : 수정할 저자의 정보를 요청을 보냄. 성공적으로 수정이 됐다면 수정한 저자의 정보를 응답 받음.
     * <br> *
     *
     * @param authorModifyRequest 수정 요청할 저자
     * @return author modify response
     * @throws RuntimeException {@code HttpStatus code}가 200이 아닌 경우
     */
    public AuthorModifyResponse modifyResponse(AuthorModifyRequest authorModifyRequest, Integer id) {
        HttpHeaders headers = Utils.getHttpHeader();
        HttpEntity<AuthorModifyRequest> requestHttpEntity = new HttpEntity<>(authorModifyRequest, headers);
        ResponseEntity<AuthorModifyResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL_ID,
                HttpMethod.PUT,
                requestHttpEntity,
                new ParameterizedTypeReference<AuthorModifyResponse>() {
                },
                id);
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    /**
     * methodName : deleteAuthor<br>
     * author : minsu11<br>
     * description : 저자 id에 대한 삭제. 정상 삭제 시 {@code AuthorDeleteResponse}를 반환
     * 삭제 실패 시 {@code RuntimeException}을 던짐
     * <br> *
     *
     * @param id 삭제할 저자 아이디
     * @return 삭제한 저자 이름이 담긴 dto
     * @throws RuntimeException 삭제 실패 시
     */
    public AuthorDeleteResponse deleteAuthor(Integer id) {
        ResponseEntity<AuthorDeleteResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL_ID,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<AuthorDeleteResponse>() {
                },
                id);
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }


}
