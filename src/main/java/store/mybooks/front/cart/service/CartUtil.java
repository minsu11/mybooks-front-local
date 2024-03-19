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
import store.mybooks.front.cart.domain.CartDetail;
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
public class CartUtil {
    public static final String CART_COOKIE = "cart";
    public final ObjectMapper objectMapper;
    private final BookAdminAdaptor bookAdminAdaptor;

    public List<CartDetail> getCartDetailList(Cookie cookie) {
        try {
            return viewCart(cookie);
        } catch (JsonProcessingException e) {
            throw new CookieParseException(e.getMessage());
        }
    }

    public void registerBookToCart(Cookie cookie, HttpServletResponse response, Long itemId, int amount) {
        try {
            List<CartDetail> cartDetailList = new ArrayList<>(viewCart(cookie));
            BookCartResponse cartBook = bookAdminAdaptor.getCartBook(itemId);
            boolean isAlreadyCart = false;
            for (CartDetail cartDetail : cartDetailList) {
                if (Objects.equals(cartBook.getId(), cartDetail.getBookId())) {
                    cartDetail.amountUpdate(amount);
                    isAlreadyCart = true;
                    break;
                }
            }

            if (!isAlreadyCart) {
                cartDetailList.add(new CartDetail(cartBook.getId(), amount, cartBook.getName(), cartBook.getBookImage(),
                        cartBook.getCost(),
                        cartBook.getSaleCost(), cartBook.getStock(), cartBook.getSellingStatus()));
            }
            String cartJson = objectMapper.writeValueAsString(cartDetailList);
            String encode = URLEncoder.encode(cartJson, StandardCharsets.UTF_8);
            Cookie saveCookie = new Cookie(CartUtil.CART_COOKIE, encode);
            response.addCookie(saveCookie);
        } catch (JsonProcessingException e) {
            throw new CookieParseException(e.getMessage());
        }
    }

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
            Cookie saveCookie = new Cookie(CartUtil.CART_COOKIE, cartJson);
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
