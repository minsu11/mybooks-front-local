package store.mybooks.front.user.controller;

import java.time.LocalDate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import store.mybooks.front.user.adaptor.UserAdaptor;
import store.mybooks.front.user.dto.request.UserCreateRequest;
import store.mybooks.front.user.dto.request.UserLoginRequest;

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

    @PostMapping("/login")
    public String loginUser(@ModelAttribute UserLoginRequest userLoginRequest) {
        System.out.println(userAdaptor.loginUser(userLoginRequest));
        return "index";
    }

    @GetMapping("/user-register")
    public String createUserForm(@ModelAttribute UserLoginRequest userLoginRequest) {
        return "register";
    }

    @PostMapping("/user-register")
    public String createUser(@ModelAttribute UserCreateRequest userCreateRequest) {
        userAdaptor.createUser(userCreateRequest);
        return "index";
    }

}
