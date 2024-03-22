package store.mybooks.front.admin.user_grade.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.admin.user_grade.adaptor.UserGradeAdaptor;
import store.mybooks.front.admin.user_grade.dto.request.UserGradeCreateRequest;

/**
 * packageName    : store.mybooks.front.admin.member_grade.controller<br>
 * fileName       : MemberGradeController<br>
 * author         : masiljangajji<br>
 * date           : 3/22/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/22/24        masiljangajji       최초 생성
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/member/grade")
public class UserGradeController {

    private final UserGradeAdaptor userGradeAdaptor;


    @GetMapping
    public String findAllAvailableUserGrade(Model model) {
        model.addAttribute("userGrades", userGradeAdaptor.findAllAvailableUserGrade());
        return "admin/view/user/grade-active";
    }

    @GetMapping("/all")
    public String findAllUserGrade(Model model) {
        model.addAttribute("userGrades", userGradeAdaptor.findAllUserGrade());
        return "admin/view/user/grade-all";
    }

    @PostMapping
    public String createUserGrade(@ModelAttribute UserGradeCreateRequest request) {
        userGradeAdaptor.createUserGrade(request);
        return "redirect:/admin/member/grade";
    }


}
