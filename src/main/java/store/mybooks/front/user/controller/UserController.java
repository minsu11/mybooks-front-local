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
 * The type User controller.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping
public class UserController {

    private final UserAdaptor userAdaptor;


    /**
     * methodName : loginUserForm
     * author : masiljangajji
     * description : 로그인 페이지로 이동
     *
     * @return string
     */
    @GetMapping("/login")
    public String loginUserForm() {
        return "login";
    }


    /**
     * methodName : createUserForm
     * author : masiljangajji
     * description : 회원가입 페이지로 이동
     *
     * @return string
     */
    @GetMapping("/user/register")
    public String createUserForm() {
        return "register";
    }


    /**
     * methodName : myPageForm
     * author : masiljangajji
     * description : mypage로 이동
     *
     * @param model 유저의 정보
     * @return string
     */
    @GetMapping("/user")
    public String myPageForm(Model model) {
        // todo JWT에서 id 꺼내쓰기
        UserGetResponse userGetResponse = userAdaptor.findUserById(1L);
        model.addAttribute("user", userGetResponse);
        return "my-page";
    }

    /**
     * methodName : userPhoneAuth
     * author : masiljangajji
     * description : 유저 회원가입 및 전화번호 변경에 필요한 인증메시지를 요청
     *
     * @return phone number auth response
     */
    @GetMapping("/user/auth/phone")
    @ResponseBody
    public PhoneNumberAuthResponse userPhoneAuth(){

        return userAdaptor.getPhoneNumberAuthResponse();
    }

    /**
     * methodName : loginUser
     * author : masiljangajji
     * description : 유저의 로그인처리
     *
     * @param userLoginRequest login request
     * @return string
     */
    @PostMapping("/login")
    public String loginUser(@ModelAttribute UserLoginRequest userLoginRequest) {
        userAdaptor.loginUser(userLoginRequest);
        return "redirect:/";
    }

    /**
     * methodName : createUser
     * author : masiljangajji
     * description : 회원가입 요청을 처리
     *
     * @param userCreateRequest create request
     * @return string
     */
    @PostMapping("/user/register")
    public String createUser(@ModelAttribute UserCreateRequest userCreateRequest) {
        userAdaptor.createUser(userCreateRequest);
        return "redirect:/";
    }

    /**
     * methodName : modifyUserPassword
     * author : masiljangajji
     * description : 유저의 비밀번호 변경
     *
     * @param modifyRequest request
     * @return string
     */
    @PostMapping("/user/modify/password")
    public String modifyUserPassword(@ModelAttribute UserPasswordModifyRequest modifyRequest) {

        // todo JWT 비밀번호 변경됐으니까 로그아웃 시키고 새로 인증받도록
        userAdaptor.modifyUserPassword(1L, modifyRequest);
        return "redirect:/";
    }

    /**
     * methodName : modifyUserStatus
     * author : masiljangajji
     * description : 유저의 상태변경
     *
     * @param modifyRequest request
     * @return string
     */
    @PostMapping("/user/modify/status")
    public String modifyUserStatus(@ModelAttribute UserStatusModifyRequest modifyRequest) {

        // todo JWT 이건 관리자용
        userAdaptor.modifyUserStatus(1L, modifyRequest);
        return "redirect:/user";
    }

    /**
     * methodName : modifyUserGrade
     * author : masiljangajji
     * description : 유저의 등급 변경
     *
     * @param modifyRequest request
     * @return string
     */
    @PostMapping("/user/modify/grade")
    public String modifyUserGrade(@ModelAttribute UserGradeModifyRequest modifyRequest) {

        // todo JWT 이건 관리자용
        userAdaptor.modifyUserGrade(1L, modifyRequest);
        return "redirect:/user";
    }


    /**
     * methodName : modifyUser
     * author : masiljangajji
     * description : 유저의 정보를 변경 (이름,전화번호)
     *
     * @param modifyRequest request
     * @return string
     */
    @PostMapping("/user/modify")
    public String modifyUser(@ModelAttribute UserModifyRequest modifyRequest) {

        // todo JWT 에서 뽑아오기

        userAdaptor.modifyUser(1L, modifyRequest);
        return "redirect:/user";
    }


    /**
     * methodName : deleteUser
     * author : masiljangajji
     * description : 회원탈퇴
     *
     * @return string
     */
    @PostMapping("/user/delete")
    public String deleteUser() {

        // todo JWT , 탈퇴했으니까 로그아웃시키고 상태관리 해줘야 함
        userAdaptor.deleteUser(2L);
        return "redirect:/";
    }


}
