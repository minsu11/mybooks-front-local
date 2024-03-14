package store.mybooks.front.home;

import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.admin.book.service.BookAdminService;
import org.springframework.data.domain.Pageable;

/**
 * packageName    : store.mybooks.front
 * fileName       : HomeController
 * author         : masiljangajji
 * date           : 2/26/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/26/24        masiljangajji       최초 생성
 */

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BookAdminService bookAdminService;


    @RequestMapping("/")
    public String home(@PageableDefault(size = 8) Pageable pageable, Model model) {
        model.addAttribute("books", bookAdminService.getBooks(pageable));
        return "index";
    }

}
