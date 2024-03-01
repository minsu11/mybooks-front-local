package store.mybooks.front.admin.return_rule.exception;

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
public class ReturnRuleRegisterFailedException extends RuntimeException {
    public ReturnRuleRegisterFailedException() {
        super("반품 규정 등록에 실패");
    }
}
