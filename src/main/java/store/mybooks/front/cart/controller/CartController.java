package store.mybooks.front.cart.controller;

import java.util.List;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import store.mybooks.front.cart.domain.CartDetail;
import store.mybooks.front.cart.service.CartUtil;
import store.mybooks.front.utils.CookieUtils;

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

    @GetMapping("/cart")
    public String viewCart(@CookieValue(name = CartUtil.CART_COOKIE, required = false) Cookie cartCookie,
                           HttpServletRequest request, Model model) {
        if (isUser(request)) {
            List<CartDetail> bookFromCart = cartUtil.getBookFromCart();

            model.addAttribute(bookFromCart);
        } else {
            List<CartDetail> cartDetailList = cartUtil.getCartDetailList(cartCookie);
            model.addAttribute(cartDetailList);
        }
        return "cart";
    }

    @GetMapping("/cart/delete")
    public String deleteItemFromCart(@CookieValue(name = CartUtil.CART_COOKIE, required = false) Cookie cartCookie,
                                     HttpServletResponse response, HttpServletRequest request,
                                     @RequestParam Long bookId) {
        if (isUser(request)) {
            cartUtil.deleteBookFromCart(bookId);
        } else {
            cartUtil.deleteBookFromCart(cartCookie, response, bookId);
        }
        return "redirect:/cart";
    }

    @PostMapping("/cart/add")
    public String addBookToCart(@CookieValue(name = CartUtil.CART_COOKIE, required = false) Cookie cartCookie,
                                HttpServletResponse response, HttpServletRequest request,
                                @RequestParam(name = "id") Long itemId,
                                @RequestParam(name = "quantity") int amount) {

        if (isUser(request)) {
            cartUtil.addBookToCart(itemId, amount);
        } else {
            cartUtil.registerBookToCart(cartCookie, response, itemId, amount);
        }
        return "redirect:/cart";
    }

    private boolean isUser(HttpServletRequest request) {
        return Objects.nonNull(CookieUtils.getIdentityCookieValue(request));
    }


//    @PostMapping("/cart/order")
//    public String cartOrder(@CookieValue(name = CartUtil.CART_COOKIE, required = false) Cookie cartCookie,
//                            HttpServletResponse response, Model model) {
//        try {
//            List<CartDetail> cartDetailList = cartUtil.viewCart(cartCookie);
//            if (cartDetailList.isEmpty()) {
//                return "redirect:/cart";
//            } else {
//                model.addAttribute("cartItem", cartDetailList);
//                String emptyCart = objectMapper.writeValueAsString(new ArrayList<CartDetail>());
//                //TODO
//            }
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        return "";
//    }


}
