package store.mybooks.front.global;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import store.mybooks.front.auth.exception.AccessIdForbiddenException;
import store.mybooks.front.auth.exception.AuthenticationIsNotValidException;
import store.mybooks.front.auth.exception.LoginFailedException;
import store.mybooks.front.auth.exception.StatusIsDormancyException;
import store.mybooks.front.auth.exception.StatusIsLockException;
import store.mybooks.front.auth.exception.TokenExpiredException;
import store.mybooks.front.order.exception.OrderInfoNotMatchException;
import store.mybooks.front.utils.CookieUtils;

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
@Slf4j
public class GlobalControllerAdvice {

    private static final String REFERER = "referer";

    private static final String domain = "https://www.my-books.store";
    


    // 토큰 인증/인가와 관련된 모든 예외를 잡음
    @ExceptionHandler({AuthenticationIsNotValidException.class, AccessIdForbiddenException.class,
            StatusIsDormancyException.class, TokenExpiredException.class, StatusIsLockException.class})
    public String handleAuthException(RuntimeException ex, HttpServletResponse response) {

        if (ex instanceof AuthenticationIsNotValidException | ex instanceof TokenExpiredException) {
            CookieUtils.deleteJwtCookie(Objects.requireNonNull(response));
            CookieUtils.deleteAdminCookie(response);
            return "redirect:/login"; // 토큰조작 됐거나 , 만료됐음 -> 다시 로그인
        } else if (ex instanceof StatusIsDormancyException) {
            return "redirect:/verification/dormancy";  // 유저계정 휴면상태
        } else if (ex instanceof StatusIsLockException) {
            return "redirect:/verification/lock"; // 유정계정 잠금상태
        }

        // 권한없는 경우 index
        return "redirect:/";
    }

    @ModelAttribute("query")
    public String lastSearchMemory(@RequestParam(value = "query", required = false) String query) {
        return query;
    }

    @ExceptionHandler({OrderInfoNotMatchException.class})
    public String handleOrderModulationException(Exception exception, HttpServletRequest request) {

        String previousUrl = request.getHeader(REFERER);
        request.getSession().setAttribute("error", exception.getMessage());
        return previousUrl.replace(domain, "redirect:");
    }


    @ExceptionHandler({LoginFailedException.class})
    public String handleLoginFailedException(Exception exception, HttpServletRequest request) {
        request.getSession().setAttribute("error", exception.getMessage());
        return "redirect:/login";
    }



    @ExceptionHandler({Exception.class}) // 발생하는 모든 예외
    public String handleRuntimeException(Exception e,Model model) {
        log.warn(e.getMessage());
        model.addAttribute("errorMessage",e.getMessage());
        return "error";
    }

}
