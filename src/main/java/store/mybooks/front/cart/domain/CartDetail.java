package store.mybooks.front.cart.domain;

import lombok.*;

/**
 * packageName    : store.mybooks.front.cart <br/>
 * fileName       : CartDetail<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/3/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/3/24        Fiat_lux       최초 생성<br/>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CartDetail {
    private Long bookId;
    private Integer cartDetailAmount;
    private String name;
    private String bookImage;
    private Integer cost;
    private Integer saleCost;
    private Integer stock;
    private String sellingStatus;

    public void addAmount(int amount) {
        this.cartDetailAmount = this.cartDetailAmount + amount;
    }

    public void amountUpdate(int amount) {
        this.cartDetailAmount = amount;
    }

    public CartDetail saleUpdate(Integer amount) {
        this.saleCost *= amount;
        return this;
    }
}
