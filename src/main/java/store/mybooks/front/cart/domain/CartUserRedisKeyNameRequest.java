package store.mybooks.front.cart.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.cart.domain <br/>
 * fileName       : CartInfoRequest<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/8/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/8/24        Fiat_lux       최초 생성<br/>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartUserRedisKeyNameRequest {
    private String cartKey;
}
