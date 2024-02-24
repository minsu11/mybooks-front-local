package store.mybooks.front.book.author.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.mybooks.front.book.author.adaptor.AuthorAdaptor;
import store.mybooks.front.book.author.dto.response.AuthorResponse;

/**
 * packageName    : store.mybooks.front.book.author.controller<br>
 * fileName       : TestController<br>
 * author         : minsu11<br>
 * date           : 2/24/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/24/24        minsu11       최초 생성<br>
 */
@RestController
@RequestMapping("test123")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorAdaptor adaptor;

    @GetMapping
    public List<AuthorResponse> test(Pageable pageable) {
        List<AuthorResponse> responses = adaptor.getAuthor().getContent();
        return responses;
    }
}
