package store.mybooks.front.user.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import store.mybooks.front.auth.adaptor.TokenAdaptor;
import store.mybooks.front.auth.dto.request.TokenCreateRequest;
import store.mybooks.front.auth.dto.response.TokenCreateResponse;
import store.mybooks.front.auth.redis.RedisAuthService;
import store.mybooks.front.config.RedisProperties;
import store.mybooks.front.user.adaptor.UserAdaptor;
import store.mybooks.front.user.dto.request.UserCreateRequest;
import store.mybooks.front.user.dto.request.UserEmailRequest;
import store.mybooks.front.user.dto.request.UserGradeModifyRequest;
import store.mybooks.front.user.dto.request.UserLoginRequest;
import store.mybooks.front.user.dto.request.UserModifyRequest;
import store.mybooks.front.user.dto.request.UserOauthRequest;
import store.mybooks.front.user.dto.request.UserPasswordModifyRequest;
import store.mybooks.front.user.dto.request.UserStatusModifyRequest;
import store.mybooks.front.user.dto.response.UserEmailCheckResponse;
import store.mybooks.front.user.dto.response.UserEncryptedPasswordResponse;
import store.mybooks.front.user.dto.response.UserGetResponse;
import store.mybooks.front.user.dto.response.UserLoginResponse;
import store.mybooks.front.user.dto.response.UserOauthCreateResponse;
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

    private final RedisAuthService redisAuthService;

    private final RedisProperties redisProperties;


    /**
     * methodName : loginUserForm
     * author : masiljangajji
     * description : 로그인 페이지로 이동
     * 이미 로그인했다면 인덱스 , 아니라면 로그인페이지로
     *
     * @param request servlet request
     * @param model model
     * @return string
     */
    @GetMapping("/login")
    public String loginUserForm(HttpServletRequest request,Model model) {

        if(Objects.nonNull(request.getSession().getAttribute("error"))){
            model.addAttribute("loginFailed",request.getSession().getAttribute("error"));
            request.getSession().removeAttribute("error");
        }

        if (Objects.isNull(request.getAttribute("identity_cookie_value"))) {
            return "login";
        }
        return "redirect:/";
    }

    /**
     * methodName : paycoLoginForm
     * author : masiljangajji
     * description : 페이코 로그인을 부름
     *
     * @return redirect view
     */
    @GetMapping("/auth/payco/login")
    public RedirectView paycoLoginForm() {

        return new RedirectView(
                "https://id.payco.com/oauth2.0/authorize?response_type=code&client_id=3RDktHA_exycIbutIzVLP3D&serviceProviderCode=FRIENDS&redirect_uri=https://www.my-books.store/login/oauth2/code/payco&state=ab42ae&userLocale=ko_KR");
    }

    /**
     * methodName : logout
     * author : masiljangajji
     * description : 로그아웃 처리
     * 쿠키를 지우는 등의 행위는 인터셉터에서 처리
     * @return string
     */
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }


    /**
     * methodName : createUserForm
     * author : masiljangajji
     * description : 회원가입 페이지로 이동
     *
     * @param request request
     * @return string
     */
    @GetMapping("/signup")
    public String createUserForm(HttpServletRequest request) {
        if (Objects.isNull(request.getAttribute("identity_cookie_value"))) {
            request.setAttribute("nowDate", LocalDate.now());
            return "signup";
        }
        return "redirect:/";
    }

    /**
     * methodName : dormancyUserForm
     * author : masiljangajji
     * description : 휴면인증페이지로 이동
     * 상태체크는 gateway 에서 함 , 휴면인증을 해제하려면 두레이 인증이 필요
     * @return string
     */
    @GetMapping("/verification/dormancy")
    public String dormancyUserForm() {
        return "dormancy";
    }

    /**
     * methodName : verifyDormancyUser
     * author : masiljangajji
     * description : dooray 인증을 받은 유저의 상태를 활성으로 변경
     *
     * @return string
     */
    @PostMapping("/dormancy")
    public String verifyDormancyUser() {
        userAdaptor.verifyDormancyUser();
        return "redirect:/";
    }

    /**
     * methodName : lockUserForm
     * author : masiljangajji
     * description : 잠금상태인 유저를 잠금페이지로 보냄
     * 모든 상태체크는 gateway 에서 진행
     * @return string
     */
    @GetMapping("/verification/lock")
    public String lockUserForm() {
        return "lock";
    }

    /**
     * methodName : verifyLockUser
     * author : masiljangajji
     * description : dooray 인증과 새로운 비밀번호를 받은 유저의 상태를 활성으로 변경
     *
     * @param request 새로운 비밀번호를 담은 dto
     * @return string
     */
    @PostMapping("/lock")
    public String verifyLockUser(@ModelAttribute UserPasswordModifyRequest request) {

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        userAdaptor.verifyLockUser(request);
        return "redirect:/";
    }

    /**
     * methodName : socialUserForm
     * author : masiljangajji
     * description : 페이코 정보제공 비동의시 추가정보를 받기위해 social 페이지로 옮김
     *
     * @param request reqeust
     * @param oauthId 페이코에서 넘겨준 oauthId
     * @return string
     */
    @GetMapping("/verification/social/{oauthId}")
    public String socialUserForm(HttpServletRequest request,@PathVariable("oauthId")String oauthId) {
        request.setAttribute("nowDate",LocalDate.now());
        request.setAttribute("oauthId",oauthId);
        return "social";
    }

    /**
     * methodName : createAndLoginOauthUser
     * author : masiljangajji
     * description : 추가인증 정보를 받은 소셜 회원에 대해 회원가입 및 로그인 처리 진행
     *
     * @param oauthRequest 회원이 입력한 추가정보
     * @param request request
     * @param response response
     * @return string
     */
    @PostMapping("/social")
    public String createAndLoginOauthUser(@ModelAttribute UserOauthRequest oauthRequest, HttpServletRequest request, HttpServletResponse response) {

        UserOauthCreateResponse createResponse = userAdaptor.createAndLoginOauthUser(oauthRequest);

        TokenCreateResponse tokenCreateResponse =
                tokenAdaptor.createToken(
                        new TokenCreateRequest(false, createResponse.getId(),
                                createResponse.getUserStatusName(), String.valueOf(UUID.randomUUID()),
                                Utils.getUserIp(request), Utils.getUserAgent(request)));

        CookieUtils.addJwtCookie(response, tokenCreateResponse.getAccessToken());
        return "redirect:/";
    }

    /**
     * methodName : myPageForm
     * author : masiljangajji
     * description : mypage로 이동
     *
     * @param model model
     * @return string
     */
    @GetMapping("/user")
    public String myPageForm(Model model) {

        UserGetResponse userGetResponse = userAdaptor.findUser();
        model.addAttribute("user", userGetResponse);
        return "my-page";
    }

    /**
     * methodName : verifyUserEmail
     * author : masiljangajji
     * description : 이메일 인증을 함
     *
     * @param email email
     * @return user email check response
     */
    @ResponseBody
    @GetMapping("/email/verify")
    public UserEmailCheckResponse verifyUserEmail(@RequestParam(name="email")String email){
        return userAdaptor.verifyUserEmail(new UserEmailRequest(email));
    }

    /**
     * methodName : loginUser
     * author : masiljangajji
     * description : 유저의 로그인처리
     *
     * @param userLoginRequest 이메일 , 비밀번호 정보
     * @param request request
     * @param response response
     * @return string
     */
    @PostMapping("/login")
    public String loginUser(@ModelAttribute UserLoginRequest userLoginRequest, HttpServletRequest request,
                            HttpServletResponse response) {

            UserEmailRequest emailRequest = new UserEmailRequest(userLoginRequest.getEmail());

            // 이메일 존재하는지 , 탈퇴했는지
            UserEncryptedPasswordResponse userEncryptedPasswordResponse =
                    userAdaptor.verifyUserStatus(emailRequest);

            // 비밀번호도 같으면 완료
            if (passwordEncoder.matches(userLoginRequest.getPassword(),
                    userEncryptedPasswordResponse.getEncryptedPassword())) {

                UserLoginResponse loginResponse = userAdaptor.completeLoginProcess(emailRequest);

                if (loginResponse.getIsAdmin()) {
                    String adminCookieValue = String.valueOf(UUID.randomUUID());
                    redisAuthService.setValues(adminCookieValue,
                            Utils.getUserIp(request) + Utils.getUserAgent(request), Duration.ofMillis(
                                    redisProperties.getAdminExpiration()));
                    CookieUtils.addAdminCookie(response, adminCookieValue);
                }
                TokenCreateResponse tokenCreateResponse =
                        tokenAdaptor.createToken(
                                new TokenCreateRequest(loginResponse.getIsAdmin(), loginResponse.getUserId(),
                                        loginResponse.getStatus(), String.valueOf(UUID.randomUUID()),
                                        Utils.getUserIp(request), Utils.getUserAgent(request)));

                // 쿠키추가
                CookieUtils.addJwtCookie(response, tokenCreateResponse.getAccessToken());

                return "redirect:/";
            }

        return "redirect:/login";
    }


    /**
     * methodName : createUser
     * author : masiljangajji
     * description : 회원가입 요청을 처리
     *
     * @param userCreateRequest 유저의 정보
     * @return string
     */
    @PostMapping("/signup")
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
     * @param modifyRequest 새로운 비밀번호
     * @return string
     */
    @PostMapping("/user/modify/password")
    public String modifyUserPassword(@ModelAttribute UserPasswordModifyRequest modifyRequest) {


        // 비밀번호 암호화
        modifyRequest.setPassword(passwordEncoder.encode(modifyRequest.getPassword()));
        userAdaptor.modifyUserPassword(modifyRequest);
        return "redirect:/";
    }

    /**
     * methodName : modifyUserStatus
     * author : masiljangajji
     * description : 유저의 상태변경
     *
     * @param userId   유저 아이디
     * @param modifyRequest 변경될 상태정보
     * @return string
     */
    @PostMapping("/user/{userId}/modify/status")
    public String modifyUserStatus(@PathVariable(name = "userId") Long userId,
                                   @ModelAttribute UserStatusModifyRequest modifyRequest) {

        userAdaptor.modifyUserStatus(userId, modifyRequest);
        return "redirect:/user";
    }

    /**
     * methodName : modifyUserGrade
     * author : masiljangajji
     * description : 유저의 등급 변경
     *
     * @param userId   유저 아이디
     * @param modifyRequest 변경될 등급정보
     * @return string
     */
    @PostMapping("/user/{userId}/modify/grade")
    public String modifyUserGrade(@PathVariable(name = "userId") Long userId,
                                  @ModelAttribute UserGradeModifyRequest modifyRequest) {
        userAdaptor.modifyUserGrade(userId, modifyRequest);
        return "redirect:/user";
    }


    /**
     * methodName : modifyUser
     * author : masiljangajji
     * description : 유저의 정보를 변경 (이름,전화번호)
     *
     * @param modifyRequest 이름 및 전화번호
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
        userAdaptor.deleteUser();
        return "redirect:/";
    }


}