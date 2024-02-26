package store.mybooks.front.book.author.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import store.mybooks.front.book.author.adaptor.AuthorAdaptor;
import store.mybooks.front.book.author.dto.request.AuthorCreateRequest;
import store.mybooks.front.book.author.dto.request.AuthorDeleteRequest;
import store.mybooks.front.book.author.dto.request.AuthorModifyRequest;
import store.mybooks.front.book.author.dto.request.AuthorRequest;
import store.mybooks.front.book.author.dto.response.AuthorCreateResponse;
import store.mybooks.front.book.author.dto.response.AuthorDeleteResponse;
import store.mybooks.front.book.author.dto.response.AuthorModifyResponse;
import store.mybooks.front.book.author.dto.response.AuthorResponse;


/**
 * packageName    : store.mybooks.front.book.author.service<br>
 * fileName       : AuthorService<br>
 * author         : minsu11<br>
 * date           : 2/24/24<br>
 * description    : 응답 받은 저자 데이터를 가공해서 view에 보여주는 service
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/24/24        minsu11       최초 생성<br>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorAdaptor adaptor;


    /**
     * methodName : getAllAuthors<br>
     * author : minsu11<br>
     * description : 조회된 저자의 모든 데이터를 반환
     * <br> *
     *
     * @return 모든 저자의 list
     */
    public List<AuthorResponse> getAllAuthors() {
        return adaptor.getAuthors().getContent();
    }

    /**
     * methodName : createAuthor<br>
     * author : minsu11<br>
     * description : 등록할 저자의 정보를 저장.
     * 저장에 성공 되면 {@code true} 반환. 실패 하면 {@code false} 반환
     * <br> *
     *
     * @param request 등록할 저자 정보
     * @return 저장한 객체의 응답 값이 {@code nonNull}인지 아닌지 따른 {@code boolean} 값 반환
     */
    public boolean createAuthor(AuthorCreateRequest request) {
        try {
            log.info("등록 메서드 시작 ");
            AuthorCreateResponse authorCreateResponse = adaptor.createAuthor(request);
            log.info("등록하고 난 후 value:{}", authorCreateResponse);
            return true;
        } catch (RuntimeException e) {

            return false;
        }
    }

    /**
     * methodName : modifyAuthor<br>
     * author : minsu11<br>
     * description : 저자의 정보가 수정. 수정이 성공이 되면 {@code true}, 실패하면 {@code false}
     * <br> *
     *
     * @param request 수정 할 저자가 담긴 DTO
     * @return boolean
     */
    public boolean updateAuthor(AuthorRequest request) {
        try {
            log.info("수정 메서드 시작");
            AuthorModifyRequest authorModifyRequest = new AuthorModifyRequest(request.getName(), request.getContent());
            AuthorModifyResponse authorModifyResponse = adaptor.modifyResponse(authorModifyRequest, request.getId());
            log.info("수정 된 저자: {}", authorModifyResponse);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * methodName : deleteAuthor<br>
     * author : minsu11<br>
     * description : 요청 들어온 저자에 대한 삭제. 삭제 성공 시 {@code true} 반환하고
     * 실패 시 {@code false}를 반환함
     * <br> *
     *
     * @param request 삭제 요청할 저자 DTO
     * @return boolean 삭제 성공 유무
     */
    public boolean deleteAuthor(AuthorDeleteRequest request) {
        try {
            log.info("delete 시작");
            AuthorDeleteResponse deleteResponse = adaptor.deleteAuthor(request.getId());
            log.info("delete response:{}", deleteResponse);

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}
