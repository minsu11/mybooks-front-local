package store.mybooks.front.admin.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.admin.member.service.MemberService;

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
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public String viewMembers(@PageableDefault Pageable pageable, Model model) {

        model.addAttribute("members", memberService.getAllMembers(pageable));

        return "admin/view/member/member-all";
    }


}


