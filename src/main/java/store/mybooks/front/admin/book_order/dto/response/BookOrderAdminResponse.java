package store.mybooks.front.admin.book_order.dto.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.admin.book_order.dto.response<br>
 * fileName       : OrderAdminResponse<br>
 * author         : minsu11<br>
 * date           : 3/3/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/3/24        minsu11       최초 생성<br>
 */
@Getter
@AllArgsConstructor
public class BookOrderAdminResponse {
    private Long id;
    private Long userId;
    private String statusId;
    private LocalDate date;
    private LocalDate outDate;
    private String invoiceNumber;
    private String number;
}
