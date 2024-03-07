package store.mybooks.front.global;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import store.mybooks.front.auth.exception.AccessIdForbiddenException;
import store.mybooks.front.auth.exception.AuthenticationIsNotValidException;
import store.mybooks.front.auth.exception.StatusIsNotActiveException;

/**
 * packageName    : store.mybooks.front.global
 * fileName       : GlobalControllerAdvice
 * author         : damho-lee
 * date           : 2/27/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/27/24          damho-lee          최초 생성
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    private static final String REFERER = "referer";

    /**
     * methodName : badRequestException <br>
     * author : damho-lee <br>
     * description : resource 서버에서 BadRequest, NotFound 상태코드가 오는 경우를 처리하는 ExceptionHandler.<br>
     *
     * @param exception HttpClientErrorException
     * @return string
     */
    @ExceptionHandler({HttpClientErrorException.BadRequest.class, HttpClientErrorException.NotFound.class})
    // 400 404 이게 리소스에서 나오는 모든 예외
    public ModelAndView handleBadRequestAndNotFoundException(Exception exception, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView(request.getHeader(REFERER));
        modelAndView.addObject("error", exception.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({AuthenticationIsNotValidException.class, AccessIdForbiddenException.class,
            StatusIsNotActiveException.class})
    public String handleAuthException(RuntimeException ex) {

        if (ex instanceof AuthenticationIsNotValidException) {
            return "redirect:/login"; // 토큰조작 다시 로그인
        } else if (ex instanceof StatusIsNotActiveException) {
            // 유저가 탈퇴했음
            return "redirect:/dormancy"; // todo 휴대폰인증 페이지로
        }

        // 권한없는 경우 index
        return "redirect:/";
    }


}
