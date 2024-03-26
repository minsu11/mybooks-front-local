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


    /**
     * methodName : findAllAvailableUserGrade
     * author : masiljangajji
     * description : 활성상태인 유저 등급을 보여줌
     *
     * @param model model
     * @return string
     */
    @GetMapping
    public String findAllAvailableUserGrade(Model model) {
        model.addAttribute("userGrades", userGradeAdaptor.findAllAvailableUserGrade());
        return "admin/view/user/grade-active";
    }

    /**
     * methodName : findAllUserGrade
     * author : masiljangajji
     * description : 활성+비활성 상태의 모든 유저 둥급을 보여줌
     *
     * @param model model
     * @return string
     */
    @GetMapping("/all")
    public String findAllUserGrade(Model model) {
        model.addAttribute("userGrades", userGradeAdaptor.findAllUserGrade());
        return "admin/view/user/grade-all";
    }

    /**
     * methodName : createUserGrade
     * author : masiljangajji
     * description : 유저등급을 생성 함
     *
     * @param request 포인트 적립률 , 등급이름
     * @return string
     */
    @PostMapping
    public String createUserGrade(@ModelAttribute UserGradeCreateRequest request) {
        userGradeAdaptor.createUserGrade(request);
        return "redirect:/admin/member/grade";
    }


}
