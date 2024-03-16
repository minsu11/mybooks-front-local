package store.mybooks.front.cart.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.book.adaptor.BookAdminAdaptor;
import store.mybooks.front.admin.book.model.response.BookCartResponse;
import store.mybooks.front.cart.controller.CartController;
import store.mybooks.front.cart.domain.CartDetail;
import store.mybooks.front.cart.domain.CartRegisterRequest;
import store.mybooks.front.cart.domain.OrderItemRequest;
import store.mybooks.front.cart.exception.CookieParseException;

/**
 * packageName    : store.mybooks.front.cart <br/>
 * fileName       : CartService<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/3/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/3/24        Fiat_lux       최초 생성<br/>
 */
@Service
@RequiredArgsConstructor
public class CartNonUserService {
    private final ObjectMapper objectMapper;
    private final BookAdminAdaptor bookAdminAdaptor;

    /**
     * Gets book from cart.
     *
     * @param cookie the cookie
     * @return the book from cart
     */
    public List<CartDetail> getBookFromCart(Cookie cookie) {
        try {
            return viewCart(cookie);
        } catch (JsonProcessingException e) {
            throw new CookieParseException(e.getMessage());
        }
    }

    /**
     * Register book to cart.
     *
     * @param cookie              the cookie
     * @param response            the response
     * @param cartRegisterRequest the cart register request
     */
    public void registerBookToCart(Cookie cookie, HttpServletResponse response,
                                   CartRegisterRequest cartRegisterRequest) {
        try {
            List<CartDetail> cartDetailList = new ArrayList<>(viewCart(cookie));
            BookCartResponse cartBook = bookAdminAdaptor.getCartBook(cartRegisterRequest.getId());
            boolean isAlreadyCart = false;
            for (CartDetail cartDetail : cartDetailList) {
                if (Objects.equals(cartBook.getId(), cartDetail.getBookId())) {
                    cartDetail.addAmount(cartRegisterRequest.getQuantity());
                    isAlreadyCart = true;
                    break;
                }
            }

            if (!isAlreadyCart) {
                cartDetailList.add(new CartDetail(
                        cartBook.getId(),
                        cartRegisterRequest.getQuantity(),
                        cartBook.getName(),
                        cartBook.getBookImage(),
                        cartBook.getCost(),
                        cartBook.getSaleCost()));
            }
            String cartJson = objectMapper.writeValueAsString(cartDetailList);
            String encode = URLEncoder.encode(cartJson, StandardCharsets.UTF_8);
            Cookie saveCookie = new Cookie(CartController.CART_COOKIE_VALUE, encode);
            response.addCookie(saveCookie);
        } catch (JsonProcessingException e) {
            throw new CookieParseException(e.getMessage());
        }
    }


    /**
     * Order book in cart.
     *
     * @param cartCookie           the cart cookie
     * @param response             the response
     * @param orderItemRequestList the order item request list
     */
    public void orderBookInCart(Cookie cartCookie, HttpServletResponse response,
                                List<OrderItemRequest> orderItemRequestList) {
        if (Objects.isNull(cartCookie)) {
            return;
        }
        try {
            List<CartDetail> cartDetailList = new ArrayList<>(viewCart(cartCookie));
            for (OrderItemRequest orderItemRequest : orderItemRequestList) {
                cartDetailList.forEach(cartDetail -> {
                    if (Objects.equals(cartDetail.getBookId(), orderItemRequest.getBookId())) {
                        cartDetail.amountUpdate(orderItemRequest.getAmount());
                    }
                });
            }
            String cartJson = objectMapper.writeValueAsString(cartDetailList);
            String encode = URLEncoder.encode(cartJson, StandardCharsets.UTF_8);
            Cookie saveCookie = new Cookie(CartController.CART_COOKIE_VALUE, encode);
            response.addCookie(saveCookie);
        } catch (JsonProcessingException e) {
            throw new CookieParseException(e.getMessage());
        }
    }


    /**
     * Delete book from cart.
     *
     * @param cookie   the cookie
     * @param response the response
     * @param bookId   the book id
     */
    public void deleteBookFromCart(Cookie cookie, HttpServletResponse response, Long bookId) {
        try {
            List<CartDetail> cartDetailList = new ArrayList<>(viewCart(cookie));
            Iterator<CartDetail> iterator = cartDetailList.iterator();
            while (iterator.hasNext()) {
                CartDetail cartDetail = iterator.next();
                if (Objects.equals(bookId, cartDetail.getBookId())) {
                    iterator.remove();
                    break;
                }
            }

            String cartJson = objectMapper.writeValueAsString(cartDetailList);
            String encode = URLEncoder.encode(cartJson, StandardCharsets.UTF_8);
            Cookie saveCookie = new Cookie(CartController.CART_COOKIE_VALUE, encode);
            response.addCookie(saveCookie);
        } catch (JsonProcessingException e) {
            throw new CookieParseException(e.getMessage());
        }
    }

    private List<CartDetail> viewCart(Cookie cartCookie) throws JsonProcessingException {
        List<CartDetail> cartDetailList = new ArrayList<>();
        if (Objects.nonNull(cartCookie)) {
            String jsonObject = cartCookie.getValue();
            String decodeJsonObject = URLDecoder.decode(jsonObject, StandardCharsets.UTF_8);
            cartDetailList = Arrays.asList(objectMapper.readValue(decodeJsonObject, CartDetail[].class));
        }

        return cartDetailList;
    }


}
