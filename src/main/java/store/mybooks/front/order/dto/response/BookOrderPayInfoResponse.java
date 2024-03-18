package store.mybooks.front.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.order.dto.response<br>
 * fileName       : BookOrderPayInfoResponse<br>
 * author         : minsu11<br>
 * date           : 3/19/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/19/24        minsu11       최초 생성<br>
 */
@Getter
@AllArgsConstructor
public class BookOrderPayInfoResponse {
    private String name;
    private String email;
    private String phoneNumber;
    private String orderNumber;
    private String orderName;
    private String orderStatus;
}
