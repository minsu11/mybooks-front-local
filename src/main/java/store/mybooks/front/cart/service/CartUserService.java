package store.mybooks.front.cart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.book.adaptor.BookAdminAdaptor;
import store.mybooks.front.admin.book.model.response.BookCartResponse;
import store.mybooks.front.cart.controller.CartController;
import store.mybooks.front.cart.domain.CartDetail;
import store.mybooks.front.cart.domain.CartRegisterRequest;
import store.mybooks.front.cart.domain.OrderItemRequest;
import store.mybooks.front.user.adaptor.UserAdaptor;
import store.mybooks.front.user.dto.response.UserGetResponse;

/**
 * packageName    : store.mybooks.front.cart.service <br/>
 * fileName       : CartUserService<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/8/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/8/24        Fiat_lux       최초 생성<br/>
 */
@Service
@RequiredArgsConstructor
public class CartUserService {

    private final BookAdminAdaptor bookAdminAdaptor;
    private final RedisTemplate<String, CartDetail> redisTemplate;
    private final UserAdaptor userAdaptor;

    /**
     * Gets book from cart.
     *
     * @return the book from cart
     */
    public List<CartDetail> getBookFromCart() {
        String cartKey = cartKey();
        List<CartDetail> cartDetailList = redisTemplate.opsForList().range(cartKey, 0, -1);

        if (Objects.isNull(cartDetailList) || cartDetailList.isEmpty()) {
            return new ArrayList<>();
        } else {
            return cartDetailList;
        }
    }

    /**
     * Add book to cart.
     *
     * @param cartRegisterRequest the cart register request
     */
    public void addBookToCart(CartRegisterRequest cartRegisterRequest) {
        String cartKey = cartKey();
        List<CartDetail> cartDetailList = redisTemplate.opsForList().range(cartKey, 0, -1);

        if (Objects.isNull(cartDetailList)) {
            cartDetailList = new ArrayList<>();
        }

        boolean isAlreadyInCart = false;

        for (CartDetail cartDetail : cartDetailList) {
            if (Objects.equals(cartRegisterRequest.getId(), cartDetail.getBookId())) {
                cartDetail.addAmount(cartRegisterRequest.getQuantity());
                isAlreadyInCart = true;
                redisTemplate.opsForList().set(cartKey, cartDetailList.indexOf(cartDetail), cartDetail);
                break;
            }
        }

        if (!isAlreadyInCart) {
            BookCartResponse cartBook = bookAdminAdaptor.getCartBook(cartRegisterRequest.getId());
            CartDetail cartDetail =
                    new CartDetail(cartBook.getId(), cartRegisterRequest.getQuantity(), cartBook.getName(),
                            cartBook.getBookImage(), cartBook.getCost(),
                            cartBook.getSaleCost(), cartBook.getStock(), cartBook.getSellingStatus());
            cartDetailList.add(cartDetail);
            redisTemplate.opsForList().rightPush(cartKey, cartDetail);
        }
    }

    /**
     * Delete book from cart.
     *
     * @param bookId the book id
     */
    public void deleteBookFromCart(Long bookId) {
        String cartKey = cartKey();
        List<CartDetail> cartDetailList = redisTemplate.opsForList().range(cartKey, 0, -1);
        if (Objects.isNull(cartDetailList) || cartDetailList.isEmpty()) {
            return;
        }
        for (CartDetail cartDetail : cartDetailList) {
            if (Objects.equals(bookId, cartDetail.getBookId())) {
                redisTemplate.opsForList().remove(cartKey, 1, cartDetail);
                break;
            }
        }
    }

    /**
     * Order book in cart.
     *
     * @param orderItemRequestList the order item request list
     */
    public void updateAmountBookInCart(List<OrderItemRequest> orderItemRequestList) {
        List<CartDetail> bookFromCart = getBookFromCart();
        if (Objects.isNull(bookFromCart) || bookFromCart.isEmpty()) {
            return;
        }

        for (OrderItemRequest orderItemRequest : orderItemRequestList) {
            bookFromCart.forEach(cartDetail -> {
                if (Objects.equals(cartDetail.getBookId(), orderItemRequest.getBookId())) {
                    cartDetail.amountUpdate(orderItemRequest.getAmount());
                }
            });
        }
        deleteAllBookFromCart();

        String carKey = cartKey();
        bookFromCart.forEach(cartDetail -> redisTemplate.opsForList().rightPush(carKey, cartDetail));
    }

    /**
     * Delete all book from cart.
     */
    public void deleteAllBookFromCart() {
        String cartKey = cartKey();
        redisTemplate.delete(cartKey);
    }

    /**
     * Cart key string.
     *
     * @return the string
     */
    public String cartKey() {
        UserGetResponse user = userAdaptor.findUser();
        return CartController.CART_COOKIE_VALUE + ":" + user.getEmail();
    }
}
