package store.mybooks.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName    : store.mybooks.front<br>
 * fileName       : TestController<br>
 * author         : minsu11<br>
 * date           : 2/23/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/23/24        minsu11       최초 생성<br>
 */
@Controller
public class TestController {
    @GetMapping("about-us")
    public String test() {
        return "about-us";
    }

    @GetMapping("blog-grid-sidebar")
    public String test1() {
        return "blog-grid-sidebar";
    }

    @GetMapping("blog-single")
    public String test2() {
        return "blog-single";
    }
}
