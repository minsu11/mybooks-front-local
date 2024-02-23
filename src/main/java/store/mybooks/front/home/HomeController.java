package store.mybooks.front.home;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import store.mybooks.front.user.user_status.adaptor.UserStatusAdaptor;
import store.mybooks.front.user.user_status.dto.response.UserStatusGetResponse;

/**
 * packageName    : store.mybooks.front
 * fileName       : HomeController
 * author         : masiljangajji
 * date           : 2/22/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/22/24        masiljangajji       최초 생성
 */
@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

}
