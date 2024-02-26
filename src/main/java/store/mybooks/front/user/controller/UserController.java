package store.mybooks.front.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import store.mybooks.front.user.adaptor.UserAdaptor;
import store.mybooks.front.user.dto.request.UserCreateRequest;
import store.mybooks.front.user.dto.request.UserGradeModifyRequest;
import store.mybooks.front.user.dto.request.UserLoginRequest;
import store.mybooks.front.user.dto.request.UserModifyRequest;
import store.mybooks.front.user.dto.request.UserPasswordModifyRequest;
import store.mybooks.front.user.dto.request.UserStatusModifyRequest;
import store.mybooks.front.user.dto.response.PhoneNumberAuthResponse;
import store.mybooks.front.user.dto.response.UserGetResponse;

/**
 * packageName    : store.mybooks.front.user.controller
 * fileName       : UserController
 * author         : masiljangajji
 * date           : 2/23/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/23/24        masiljangajji       최초 생성
 */

@Controller
@RequiredArgsConstructor
@RequestMapping
public class UserController {

    private final UserAdaptor userAdaptor;

    @GetMapping("/login")
    public String loginUserForm() {
        return "login";
    }

    @GetMapping("/user/register")
    public String createUserForm(@ModelAttribute UserLoginRequest userLoginRequest,Model model) {
        UserGetResponse userGetResponse = userAdaptor.findUserById(1L);
        model.addAttribute("userId",1L);
        model.addAttribute("user", userGetResponse);

        return "real-test";
    }

    @GetMapping("/user")
    public String myPageForm(Model model) {
        // todo JWT에서 id 꺼내쓰기
        UserGetResponse userGetResponse = userAdaptor.findUserById(1L);
        model.addAttribute("userId",1L);
        model.addAttribute("user", userGetResponse);
        return "my-page";
    }

    @GetMapping("/user/auth/phone")
    public String userPhoneAuth(Model model){

        PhoneNumberAuthResponse phoneNumberAuthResponse = userAdaptor.getPhoneNumberAuthResponse();
        model.addAttribute("phoneNumberAuth",phoneNumberAuthResponse.getRandomNumber());
        return "/";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute UserLoginRequest userLoginRequest) {
        userAdaptor.loginUser(userLoginRequest);
        return "redirect:/";
    }

    @PostMapping("/user/register")
    public String createUser(@ModelAttribute UserCreateRequest userCreateRequest) {
        userAdaptor.createUser(userCreateRequest);
        return "redirect:/";
    }

    @PostMapping("/user/modify/password")
    public String modifyUserPassword(@ModelAttribute UserPasswordModifyRequest modifyRequest) {

        // todo JWT 비밀번호 변경됐으니까 로그아웃 시키고 새로 인증받도록
        userAdaptor.modifyUserPassword(1L, modifyRequest);
        return "redirect:/";
    }

    @PostMapping("/user/modify/status")
    public String modifyUserStatus(@ModelAttribute UserStatusModifyRequest modifyRequest) {

        // todo JWT 이건 관리자용
        userAdaptor.modifyUserStatus(1L, modifyRequest);
        return "redirect:/user";
    }

    @PostMapping("/user/modify/grade")
    public String modifyUserGrade(@ModelAttribute UserGradeModifyRequest modifyRequest) {

        // todo JWT 이건 관리자용
        userAdaptor.modifyUserGrade(1L, modifyRequest);
        return "redirect:/user";
    }


    @PostMapping("/user/modify")
    public String modifyUser(@RequestParam("userId")Long userId, @ModelAttribute UserModifyRequest modifyRequest) {

        userAdaptor.modifyUser(userId, modifyRequest);
        return "redirect:/user";
    }


    @PostMapping("/user/delete")
    public String deleteUser() {

        // todo JWT , 탈퇴했으니까 로그아웃시키고 상태관리 해줘야 함
        userAdaptor.deleteUser(2L);
        return "redirect:/";
    }


}
