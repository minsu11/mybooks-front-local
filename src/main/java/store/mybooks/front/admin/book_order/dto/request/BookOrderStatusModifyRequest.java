package store.mybooks.front.admin.book_order.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.admin.book_order.dto.request<br>
 * fileName       : BookOrderStatusModifyRequest<br>
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
public class BookOrderStatusModifyRequest {
    @Positive
    private Long id;

    @NotBlank
    private String invoiceNumber;
}
