package store.mybooks.front.book.author.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import store.mybooks.front.book.author.dto.request.AuthorCreateRequest;
import store.mybooks.front.book.author.dto.request.AuthorModifyRequest;
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
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/authors")
public class AuthorController {
    private final AuthorService authorService;


    @GetMapping
    public String viewAuthor(ModelMap modelMap) {
        log.info("저자 폼");

        List<AuthorResponse> authorResponseList = authorService.getAllAuthors();
        modelMap.put("authorList", authorResponseList);
        // todo 관리자 권한이 있는 사람만 가능하게
        return "admin/view/author-view";
    }

    @GetMapping("/register")
    public String viewAuthorRegister() {

        return "admin/view/author-register-view";
    }


    @PostMapping("/register")
    public String doRegisterAuthor(
            @ModelAttribute AuthorCreateRequest createRequest) {

        log.info("=======>value: {}", createRequest.getName());
        if (authorService.createAuthor(createRequest)) {
            log.info("확인");
            return "redirect:/admin/authors";
        }
        return "redirect:/admin/authors/register";
    }

    /**
     * methodName : viewModifyForm<br>
     * author : minsu11<br>
     * description : 수정 요청 시 수정 {@code view}를 보여주는 메서드
     * <br> *
     *
     * @param modelMap 수정할 저자 데이터를 view에 전달해주는 modelMap
     * @param id       저자의 id
     * @return {@code view}가 저장된 경로
     */
    @GetMapping("/update")
    public String viewModifyForm(ModelMap modelMap,
                                 @ModelAttribute Integer id) {

        return "admin/view/author-register-view";
    }

    @PutMapping("/update")
    public String doModifyAuthor(ModelMap map,
                                 @ModelAttribute Integer id,
                                 @ModelAttribute AuthorModifyRequest request) {

        log.info("modify request: {}", request);
        if (authorService.modifyAuthor(request, id)) {
            log.info("if문 진입");
            return "redirect:/admin/authors";
        }
        return "redirect:/admin/authors/modify";
    }

  
}
