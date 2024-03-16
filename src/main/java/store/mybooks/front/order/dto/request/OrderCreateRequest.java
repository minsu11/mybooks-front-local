package store.mybooks.front.order.dto.request;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.order.dto.request<br>
 * fileName       : OrderCreateRequest<br>
 * author         : minsu11<br>
 * date           : 3/16/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/16/24        minsu11       최초 생성<br>
 */
@Getter
@AllArgsConstructor
public class OrderCreateRequest {
    private List<BookInfoRequest> bookInfoList;
    private String deliveryName;
    private LocalDate deliveryDate;
    private Integer point;
    private String recipientName;
    private String recipientAddress;
    private String recipientPhoneNumber;
    private String receiverMessage;
    private String orderNumber;
}
