package store.mybooks.front.global.exception;

import lombok.Getter;

/**
 * packageName    : store.mybooks.front.admin.return_rule.exception<br>
 * fileName       : ReturnRuleRegisterFailedException<br>
 * author         : minsu11<br>
 * date           : 3/1/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/1/24        minsu11       최초 생성<br>
 */
@Getter
public class RequestRegisterFailedException extends RuntimeException {
    private final String url;

    public RequestRegisterFailedException(String url) {
        super("등록 실패");
        this.url = url;
    }
}
