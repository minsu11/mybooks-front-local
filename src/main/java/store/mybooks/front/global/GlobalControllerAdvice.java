package store.mybooks.front.global;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

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
    /**
     * methodName : badRequestException <br>
     * author : damho-lee <br>
     * description : resource 서버에서 BadRequest, NotFound 상태코드가 오는 경우를 처리하는 ExceptionHandler.<br>
     *
     * @param exception HttpClientErrorException
     * @param model Model
     * @return string
     */
    @ExceptionHandler({HttpClientErrorException.BadRequest.class, HttpClientErrorException.NotFound.class})
    public String badRequestException(HttpClientErrorException exception, Model model) {
        if (exception.getMessage() != null) {
            model.addAttribute("message", exception.getMessage());
        }
        return "/admin/view/error";
    }
}
