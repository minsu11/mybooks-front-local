package store.mybooks.front.order.dto.response;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * packageName    : store.mybooks.resource.book_order.dto.response
 * fileName       : BookOrderResponse
 * author         : minsu11
 * date           : 2/15/24
 * description    : 회원아이디, 주문 상태 명, 배송 규정 아이디,
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/15/24        minsu11       최초 생성
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookOrderUserResponse {
    private Long id;
    private String statusId;
    private String deliveryRuleName;
    private Integer deliveryCost;
    private LocalDate orderDate;
    private String invoiceNumber;
    private String receiverName;
    private String receiverAddress;
    private String receiverPhoneNumber;
    private String receiverMessage;
    private Integer totalCost;
    private Integer pointCost;
    private Integer couponCost;
    private String number;
    private List<BookOrderDetailInfoResponse> orderDetailInfoList;

}
