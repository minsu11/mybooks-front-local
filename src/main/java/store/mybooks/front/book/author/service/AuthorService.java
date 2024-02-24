package store.mybooks.front.book.author.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.mybooks.front.book.author.adaptor.AuthorAdaptor;
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
        return adaptor.getAuthor().getContent();
    }

    public void createAuthor() {
        
    }
}
