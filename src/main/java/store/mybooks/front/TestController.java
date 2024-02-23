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

    @GetMapping("blog-single-sidebar")
    public String test3() {
        return "blog-single-sidebar";
    }

    @GetMapping("cart")
    public String test4() {
        return "cart";
    }

    @GetMapping("checkout")
    public String test5() {
        return "checkout";
    }

    @GetMapping("contact")
    public String test6() {
        return "contact";
    }

    @GetMapping("faq")
    public String test7() {
        return "faq";
    }

    @GetMapping("index")
    public String test8() {
        return "index";
    }

    @GetMapping("login")
    public String test9() {
        return "login";
    }

    @GetMapping("product-details")
    public String test10() {
        return "product-details";
    }

    @GetMapping("product-grids")
    public String test11() {
        return "product-grids";
    }
}
