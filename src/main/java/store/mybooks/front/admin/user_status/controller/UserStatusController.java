package store.mybooks.front.admin.user_status.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.admin.user_status.adaptor.UserStatusAdaptor;

/**
 * packageName    : store.mybooks.resource.user_status.controller<br>
 * fileName       : UserStatusRestController<br>
 * author         : masiljangajji<br>
 * date           : 2/18/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/18/24        masiljangajji       최초 생성
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/member/status")
public class UserStatusController {

    private final UserStatusAdaptor userStatusAdaptor;


    @GetMapping
    public String findUserStatusById(Model model) {
        model.addAttribute("userStatus", userStatusAdaptor.findAllUserStatus());

        return "admin/view/user/status";
    }


}
