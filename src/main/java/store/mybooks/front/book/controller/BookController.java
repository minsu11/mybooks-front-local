package store.mybooks.front.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName    : store.mybooks.front.book.controller<br>
 * fileName       : BookController<br>
 * author         : minsu11<br>
 * date           : 2/22/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/22/24        minsu11       최초 생성<br>
 */
@Controller
@RequestMapping("book")
public class BookController {
    public String get() {

        return "layout";
    }
}
