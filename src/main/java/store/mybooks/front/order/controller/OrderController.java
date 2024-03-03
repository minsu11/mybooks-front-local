package store.mybooks.front.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName    : store.mybooks.front.order.controller<br>
 * fileName       : OrderController<br>
 * author         : minsu11<br>
 * date           : 3/1/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/1/24        minsu11       최초 생성<br>
 */
@Controller
@RequiredArgsConstructor
public class OrderController {
    @GetMapping("direct")
    public String viewOrderPage() {
        return "checkout";
    }


}
