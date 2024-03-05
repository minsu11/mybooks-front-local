package store.mybooks.front.oauth2;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : store.mybooks.front.oauth2<br>
 * fileName       : OauthRestController<br>
 * author         : masiljangajji<br>
 * date           : 3/3/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/3/24        masiljangajji       최초 생성
 */
@Controller
public class OauthRestController {

    private final OauthService oauthService;

    public OauthRestController(OauthService oauthService) {
        this.oauthService = oauthService;
    }

    @GetMapping("/login/oauth2/code/{provider}")
    public String login(@PathVariable String provider, @RequestParam String code) {

        OauthLoginResponse loginResponse = oauthService.login(provider, code);
        return "redirect:/";
    }


}