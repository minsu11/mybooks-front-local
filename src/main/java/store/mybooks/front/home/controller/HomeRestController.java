package store.mybooks.front.home.controller;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import store.mybooks.front.home.dto.response.UserCheckResponse;
import store.mybooks.front.utils.CookieUtils;

/**
 * packageName    : store.mybooks.front.order.controller<br>
 * fileName       : HomeRestController<br>
 * author         : minsu11<br>
 * date           : 3/27/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/27/24        minsu11       최초 생성<br>
 */
@RestController
@RequiredArgsConstructor
public class HomeRestController {

    /**
     * methodName : checkUser<br>
     * author : minsu11<br>
     * description : 비회원의 주문내역을 화면에 보여주기 전에 유저인지 아닌지 확이.
     *
     * @param request the request
     * @return the response entity
     */
    @GetMapping("/home/user/identity")
    public ResponseEntity<UserCheckResponse> checkUser(HttpServletRequest request) {
        UserCheckResponse response = new UserCheckResponse(false);
        if (Objects.nonNull(CookieUtils.getIdentityCookieValue(request))) {
            response.changeCheckResult(true);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

}
