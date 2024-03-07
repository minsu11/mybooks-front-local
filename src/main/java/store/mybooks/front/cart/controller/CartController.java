package store.mybooks.front.cart.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import store.mybooks.front.admin.book.adaptor.BookAdminAdaptor;
import store.mybooks.front.admin.book.model.response.BookCartResponse;
import store.mybooks.front.cart.domain.CartDetail;
import store.mybooks.front.cart.service.CartUtil;

/**
 * packageName    : store.mybooks.front.cart <br/>
 * fileName       : CartController<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/3/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/3/24        Fiat_lux       최초 생성<br/>
 */
@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartUtil cartUtil;
    private final BookAdminAdaptor bookAdminAdaptor;
    private final ObjectMapper objectMapper;

    @GetMapping("/cart")
    public String viewCart(@CookieValue(name = CartUtil.CART_COOKIE, required = false) Cookie cartCookie, Model model) {
        try {
            List<CartDetail> cartDetailList = cartUtil.viewCart(cartCookie);
            System.out.println(cartDetailList);

            model.addAttribute(cartDetailList);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return "cart";
    }

    @GetMapping("/cart/delete")
    public String deleteItemFromCart(@CookieValue(name = CartUtil.CART_COOKIE, required = false) Cookie cartCookie,
                                                   HttpServletResponse response,
                                                   @RequestParam Long bookId) {
        try {
            List<CartDetail> cartDetailList = new ArrayList<>(cartUtil.viewCart(cartCookie));

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

            return "redirect:/cart";

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/cart/add")
    public String addBookToCart(@CookieValue(name = CartUtil.CART_COOKIE, required = false) Cookie cartCookie,
                                HttpServletResponse response, @RequestParam(name = "id") Long itemId,
                                @RequestParam(name = "quantity") int amount) {
        try {
            List<CartDetail> cartDetailList = new ArrayList<>(cartUtil.viewCart(cartCookie));
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
                        cartBook.getSaleCost()));
            }
            String cartJson = objectMapper.writeValueAsString(cartDetailList);
            String encode = URLEncoder.encode(cartJson, StandardCharsets.UTF_8);
            Cookie saveCookie = new Cookie(CartUtil.CART_COOKIE, encode);
            response.addCookie(saveCookie);

            return "redirect:/cart";
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/cart/order")
    public String cartOrder(@CookieValue(name = CartUtil.CART_COOKIE, required = false) Cookie cartCookie,
                            HttpServletResponse response, Model model) {
        try {
            List<CartDetail> cartDetailList = cartUtil.viewCart(cartCookie);
            if (cartDetailList.isEmpty()) {
                return "redirect:/cart";
            } else {
                model.addAttribute("cartItem", cartDetailList);
                String emptyCart = objectMapper.writeValueAsString(new ArrayList<CartDetail>());
                //TODO
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return "";
    }


}
