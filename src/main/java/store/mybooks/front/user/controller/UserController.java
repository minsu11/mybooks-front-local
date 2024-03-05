package store.mybooks.front.user.controller;

import java.util.Arrays;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.auth.adaptor.TokenAdaptor;
import store.mybooks.front.auth.dto.request.TokenCreateRequest;
import store.mybooks.front.auth.dto.response.TokenCreateResponse;
import store.mybooks.front.user.adaptor.UserAdaptor;
import store.mybooks.front.user.dto.request.UserCreateRequest;
import store.mybooks.front.user.dto.request.UserGradeModifyRequest;
import store.mybooks.front.user.dto.request.UserLoginRequest;
import store.mybooks.front.user.dto.request.UserModifyRequest;
import store.mybooks.front.user.dto.request.UserPasswordModifyRequest;
import store.mybooks.front.user.dto.request.UserStatusModifyRequest;
import store.mybooks.front.user.dto.response.UserGetResponse;
import store.mybooks.front.user.dto.response.UserLoginResponse;
import store.mybooks.front.utils.CookieUtils;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.user.controller<br>
 * fileName       : UserController<br>
 * author         : masiljangajji<br>
 * date           : 2/23/24<br>
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

    private final TokenAdaptor tokenAdaptor;

    private final PasswordEncoder passwordEncoder;

    /**
     * methodName : loginUserForm
     * author : masiljangajji
     * description : 로그인 페이지로 이동
     *
     * @return string
     */
    @GetMapping("/login")
    public String loginUserForm(HttpServletRequest request) {

        if (Objects.isNull(request.getAttribute("identity_cookie_value"))) {
            return "login";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        CookieUtils.deleteJwtCookie(response);
        return "redirect:/";
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

        UserGetResponse userGetResponse = userAdaptor.findUser();
        model.addAttribute("user", userGetResponse);
        return "my-page";
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
    public String loginUser(@ModelAttribute UserLoginRequest userLoginRequest, HttpServletResponse response) {

        // 비밀번호 암호화
        userLoginRequest.setPassword(passwordEncoder.encode(userLoginRequest.getPassword()));

        // 여기서 검증받고
        UserLoginResponse loginResponse = userAdaptor.loginUser(userLoginRequest);

        // 검증됐으면
        if (loginResponse.getIsValidUser()) {
            // 토큰 값 가져오고
            TokenCreateResponse tokenCreateResponse =
                    tokenAdaptor.createToken(
                            new TokenCreateRequest(loginResponse.getIsAdmin(), loginResponse.getUserId(),
                                    loginResponse.getStatus()));

            CookieUtils.addJwtCookie(response, tokenCreateResponse.getAccessToken());
            return "redirect:/";

        } else {
            // 실패하면 다시 로그인 창으로
            return "redirect:/login";
        }

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

        // 비밀번호 암호화
        userCreateRequest.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));

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

        // 비밀번호 암호화
        modifyRequest.setPassword(passwordEncoder.encode(modifyRequest.getPassword()));

        // todo JWT 비밀번호 변경됐으니까 로그아웃 시키고 새로 인증받도록
        userAdaptor.modifyUserPassword(modifyRequest);
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
    @PostMapping("/user/{userId}/modify/status")
    public String modifyUserStatus(@PathVariable(name = "userId") Long userId,
                                   @ModelAttribute UserStatusModifyRequest modifyRequest) {

        // todo JWT 이건 관리자용
        userAdaptor.modifyUserStatus(userId, modifyRequest);
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
    @PostMapping("/user/{userId}/modify/grade")
    public String modifyUserGrade(@PathVariable(name = "userId") Long userId,
                                  @ModelAttribute UserGradeModifyRequest modifyRequest) {

        // todo JWT 이건 관리자용
        userAdaptor.modifyUserGrade(userId, modifyRequest);
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
        userAdaptor.modifyUser(modifyRequest);
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
        userAdaptor.deleteUser();
        return "redirect:/";
    }


}