package store.mybooks.front.order.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@ToString
public class OrderInfoRequest {

    private String recipientName;
    private String recipientPhoneNumber;
    private Long addressId;
    private String recipientAddress;
    private String deliveryDate;
    private String receiverMessage;
    private Integer usingPoint;
    private Integer wrapCost;
    private Integer couponApplicationAmount;


}
