package store.mybooks.front.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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


    /**
     * Login user form string.
     * Login 페이지로 매핑
     * @return the string
     */
    @GetMapping("/login")
    public String loginUserForm() {
        return "login";
    }


    /**
     * Create user form string.
     * 회원가입 페이지로 매핑
     * @param model the model 유저정보를 담기위한 객체
     * @return the string
     */
    @GetMapping("/user/register")
    public String createUserForm(Model model) {
        UserGetResponse userGetResponse = userAdaptor.findUserById(1L);
        model.addAttribute("user", userGetResponse);

        return "register";
    }


    /**
     * My page form string.
     * 유저 MyPage 로 매핑
     * @param model the model 유저정보를 담기위한 객체
     * @return the string
     */
    @GetMapping("/user")
    public String myPageForm(Model model) {
        // todo JWT에서 id 꺼내쓰기
        UserGetResponse userGetResponse = userAdaptor.findUserById(1L);
        model.addAttribute("user", userGetResponse);
        return "my-page";
    }

    /**
     * User phone auth phone number auth response.
     * 유저가 전화번호를 변경하거나 , 회원가입시 Dooray로 메시지 인증받기 위한 메서드
     * @return the phone number auth response
     */
    @GetMapping("/user/auth/phone")
    @ResponseBody
    public PhoneNumberAuthResponse userPhoneAuth(){

        return userAdaptor.getPhoneNumberAuthResponse();
    }

    /**
     * Login user string.
     * 유저의 로그인 요청을 처리함
     * @param userLoginRequest the user login request
     * @return the string
     */
    @PostMapping("/login")
    public String loginUser(@ModelAttribute UserLoginRequest userLoginRequest) {
        userAdaptor.loginUser(userLoginRequest);
        return "redirect:/";
    }

    /**
     * Create user string.
     * 유저의 회원가입 요청을 처리함
     * @param userCreateRequest the user create request
     * @return the string
     */
    @PostMapping("/user/register")
    public String createUser(@ModelAttribute UserCreateRequest userCreateRequest) {
        userAdaptor.createUser(userCreateRequest);
        return "redirect:/";
    }

    /**
     * Modify user password string.
     * 유저의 비밀번호 변경 요청을 처리함
     * @param modifyRequest the modify request
     * @return the string
     */
    @PostMapping("/user/modify/password")
    public String modifyUserPassword(@ModelAttribute UserPasswordModifyRequest modifyRequest) {

        // todo JWT 비밀번호 변경됐으니까 로그아웃 시키고 새로 인증받도록
        userAdaptor.modifyUserPassword(1L, modifyRequest);
        return "redirect:/";
    }

    /**
     * Modify user status string.
     * 유저의 상태변경을 처리함
     * @param modifyRequest the modify request
     * @return the string
     */
    @PostMapping("/user/modify/status")
    public String modifyUserStatus(@ModelAttribute UserStatusModifyRequest modifyRequest) {

        // todo JWT 이건 관리자용
        userAdaptor.modifyUserStatus(1L, modifyRequest);
        return "redirect:/user";
    }

    /**
     * Modify user grade string.
     * 유저의 등급변경을 처리함
     * @param modifyRequest the modify request
     * @return the string
     */
    @PostMapping("/user/modify/grade")
    public String modifyUserGrade(@ModelAttribute UserGradeModifyRequest modifyRequest) {

        // todo JWT 이건 관리자용
        userAdaptor.modifyUserGrade(1L, modifyRequest);
        return "redirect:/user";
    }


    /**
     * Modify user string.
     * 유저의 정보 (이름,전화번호) 변경을 처리함
     * @param modifyRequest the modify request
     * @return the string
     */
    @PostMapping("/user/modify")
    public String modifyUser(@ModelAttribute UserModifyRequest modifyRequest) {

        // todo JWT 에서 뽑아오기

        userAdaptor.modifyUser(1L, modifyRequest);
        return "redirect:/user";
    }


    /**
     * Delete user string.
     * 유저 탈퇴를 처리함
     * @return the string
     */
    @PostMapping("/user/delete")
    public String deleteUser() {

        // todo JWT , 탈퇴했으니까 로그아웃시키고 상태관리 해줘야 함
        userAdaptor.deleteUser(2L);
        return "redirect:/";
    }


}
