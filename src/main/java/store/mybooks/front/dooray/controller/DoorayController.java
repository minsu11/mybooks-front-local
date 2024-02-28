package store.mybooks.front.dooray.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import store.mybooks.front.dooray.adaptor.DoorayAdaptor;
import store.mybooks.front.dooray.dto.DoorayAuthResponse;
import store.mybooks.front.user.adaptor.UserAdaptor;

/**
 * packageName    : store.mybooks.front.dooray.controller<br>
 * fileName       : DoorayController<br>
 * author         : masiljangajji<br>
 * date           : 2/28/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/28/24        masiljangajji       최초 생성
 */

@RestController
@RequiredArgsConstructor
public class DoorayController {

    private final DoorayAdaptor doorayAdaptor;


    /**
     * methodName : userPhoneAuth
     * author : masiljangajji
     * description : 유저 회원가입 및 전화번호 변경에 필요한 인증메시지를 요청
     *
     * @return phone number auth response
     */
    @GetMapping("/dooray")
    @ResponseBody
    public DoorayAuthResponse userPhoneAuth() {

        return doorayAdaptor.getDoorayAuthNumber();
    }

}
