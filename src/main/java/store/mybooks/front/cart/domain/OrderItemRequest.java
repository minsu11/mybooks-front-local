package store.mybooks.front.cart.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.cart.domain <br/>
 * fileName       : OrderItemDto<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/16/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/16/24        Fiat_lux       최초 생성<br/>
 */
@NoArgsConstructor
@Getter
public class OrderItemRequest {
    private Long bookId;
    private int amount;
}
