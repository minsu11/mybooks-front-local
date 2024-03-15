package store.mybooks.front.order.dto.request;

import lombok.*;

/**
 * packageName    : store.mybooks.front.order.dto.request<br>
 * fileName       : OrderRequest<br>
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
@AllArgsConstructor
@ToString
public class OrderInfoRequest {
    private String userId;
    private String email;
    private String phoneNumber;
    private String recipientName;
    private String recipientPhoneNumber;
    private String recipientAddress;

}
