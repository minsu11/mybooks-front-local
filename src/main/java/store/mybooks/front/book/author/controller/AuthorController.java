package store.mybooks.front.book.author.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.book.author.dto.response.AuthorResponse;
import store.mybooks.front.book.author.service.AuthorService;

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
    private final AuthorService authorService;

    @GetMapping
    public String viewAuthor(ModelMap modelMap) {

        List<AuthorResponse> authorResponseList = authorService.getAllAuthors();
        modelMap.put("authorList", authorResponseList);
        // todo 관리자 권한이 있는 사람만 가능하게
        return "admin/author-view";
    }

    @GetMapping("register")
    public String viewAuthorRegister() {

        return "admin/view/author-register-view";
    }

    @PostMapping
    public String doRegisterAuthor() {
        return "redirect:admin/view/author-view";
    }

}
