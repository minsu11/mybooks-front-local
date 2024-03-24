package store.mybooks.front.order.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : store.mybooks.front.order.dto.request<br>
 * fileName       : OrderUserInfoRequest<br>
 * author         : minsu11<br>
 * date           : 3/16/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/16/24        minsu11       최초 생성<br>
 */
@Getter
@Setter
@NoArgsConstructor
public class OrderUserInfoRequest {
    private String userName;
    private String email;
    private String phoneNumber;
    private Long addressId;
    private String orderCode;
}
