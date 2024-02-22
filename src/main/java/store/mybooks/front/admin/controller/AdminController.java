package store.mybooks.front.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName    : store.mybooks.front.admin.controller<br>
 * fileName       : AdminController<br>
 * author         : minsu11<br>
 * date           : 2/22/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/22/24        minsu11       최초 생성<br>
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public String doAdmin() {
        return "admin";

    }
}
