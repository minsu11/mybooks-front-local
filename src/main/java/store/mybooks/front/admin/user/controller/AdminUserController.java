package store.mybooks.front.admin.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.admin.user.adaptor.AdminUserAdaptor;

/**
 * packageName    : store.mybooks.front.admin.member<br>
 * fileName       : MemberController<br>
 * author         : masiljangajji<br>
 * date           : 3/15/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/15/24        masiljangajji       최초 생성
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/member")
public class AdminUserController {

    private final AdminUserAdaptor adminUserAdaptor;

    @GetMapping
    public String viewMembers(@PageableDefault Pageable pageable, Model model) {

        model.addAttribute("users", adminUserAdaptor.getPagedUsers(pageable));

        return "admin/view/user/user-all";
    }


}


