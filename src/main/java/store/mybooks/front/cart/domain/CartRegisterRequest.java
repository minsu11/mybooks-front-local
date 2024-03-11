package store.mybooks.front.cart.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : store.mybooks.front.cart.domain <br/>
 * fileName       : CartRegisterRequest<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/8/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/8/24        Fiat_lux       최초 생성<br/>
 */
@Getter
@Setter
@NoArgsConstructor
public class CartRegisterRequest {
    private Long id;
    private int quantity;
}
