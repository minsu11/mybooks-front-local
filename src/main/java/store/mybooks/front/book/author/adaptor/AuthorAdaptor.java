package store.mybooks.front.book.author.adaptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.book.author.dto.request.AuthorCreateRequest;
import store.mybooks.front.book.author.dto.request.AuthorModifyRequest;
import store.mybooks.front.book.author.dto.response.AuthorCreateResponse;
import store.mybooks.front.book.author.dto.response.AuthorDeleteResponse;
import store.mybooks.front.book.author.dto.response.AuthorModifyResponse;
import store.mybooks.front.book.author.dto.response.AuthorResponse;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.pageable.dto.response.PageResponse;

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

    /**
     * methodName : getAuthor<br>
     * author : minsu11<br>
     * description : 요청한 {@code id}를 통해 저자의 정보를 조회
     * <br> *
     *
     * @param id 조회할 저자의 {@code id}
     * @return author response
     * @throws RuntimeException {@code Http status code ok}가 나오지 않는 경우
     */
    public AuthorResponse getAuthor(Integer id) {
        ResponseEntity<AuthorResponse> exchange = restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/authors/{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<AuthorResponse>() {
                }, id);
        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
        return exchange.getBody();
    }

    /**
     * methodName : getAuthors<br>
     * author : minsu11<br>
     * description : 모든 저자의 정보를 조회
     * <br> *
     *
     * @return page response
     */
    public PageResponse<AuthorResponse> getAuthors() {

        ResponseEntity<PageResponse<AuthorResponse>> exchange = restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/authors",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageResponse<AuthorResponse>>() {
                });
        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
        return exchange.getBody();
    }

    /**
     * methodName : createAuthor<br>
     * author : minsu11<br>
     * description : {@code AuthorCreateRequest}를 {@code gateway}로 요청 보내
     * 저자를 등록한 후 {@code AuthorCreateResponse}로 응답 받음
     * <br> *
     *
     * @param authorCreateRequest 등록할 저자
     * @return authorCreateResponse 등록하고 응답 받은 데이터
     */
    public AuthorCreateResponse createAuthor(AuthorCreateRequest authorCreateRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<AuthorCreateRequest> requestHttpEntity = new HttpEntity<>(authorCreateRequest, headers);
        ResponseEntity<AuthorCreateResponse> exchange = restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/authors",
                HttpMethod.POST,
                requestHttpEntity,
                new ParameterizedTypeReference<AuthorCreateResponse>() {
                });

        if (exchange.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException();
        }
        return exchange.getBody();
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
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<AuthorModifyRequest> requestHttpEntity = new HttpEntity<>(authorModifyRequest, headers);
        ResponseEntity<AuthorModifyResponse> exchange = restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/authors/{id}",
                HttpMethod.PUT,
                requestHttpEntity,
                new ParameterizedTypeReference<AuthorModifyResponse>() {
                }, id);
        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
        return exchange.getBody();
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
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<AuthorDeleteResponse> request = new HttpEntity<>(headers);
        ResponseEntity<AuthorDeleteResponse> exchange = restTemplate.exchange(gatewayAdaptorProperties.getAddress() + "/api/authors/{id}",
                HttpMethod.DELETE,
                request,
                new ParameterizedTypeReference<AuthorDeleteResponse>() {
                }, id);
        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
        return exchange.getBody();
    }
}
