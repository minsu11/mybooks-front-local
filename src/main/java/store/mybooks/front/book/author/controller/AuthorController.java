package store.mybooks.front.book.author.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.book.author.adaptor.AuthorAdaptor;

/**
 * packageName    : store.mybooks.front.book.author.controller<br>
 * fileName       : AuthorController<br>
 * author         : minsu11<br>
 * date           : 2/24/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/24/24        minsu11       최초 생성<br>
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/author")
public class AuthorController {
    private final AuthorAdaptor adaptor;

    @GetMapping
    public String viewAuthor() {
        // todo 관리자 권한이 있는 사람만 가능하게
        return "author-view";
    }

    @GetMapping("register")
    public String viewAuthorRegister() {
        return "admin/view/author-register-view";
    }
}
