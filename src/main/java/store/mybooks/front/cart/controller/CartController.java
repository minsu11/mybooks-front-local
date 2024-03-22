package store.mybooks.front.cart.controller;

import java.util.List;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import store.mybooks.front.cart.domain.CartDetail;
import store.mybooks.front.cart.domain.CartRegisterRequest;
import store.mybooks.front.cart.domain.OrderItemRequest;
import store.mybooks.front.cart.service.CartNonUserService;
import store.mybooks.front.cart.service.CartUserService;
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
    private final CartNonUserService cartNonUserService;
    private final CartUserService cartUserService;

    public static final String CART_COOKIE_VALUE = "cart";

    /**
     * View cart string.
     *
     * @param cartCookie the cart cookie
     * @param request    the request
     * @param model      the model
     * @return the string
     */
    @GetMapping("/cart")
    public String viewCart(@CookieValue(name = CART_COOKIE_VALUE, required = false) Cookie cartCookie,
                           HttpServletRequest request, Model model) {
        if (isUser(request)) {
            List<CartDetail> bookFromCart = cartUserService.getBookFromCart();

            model.addAttribute(bookFromCart);
        } else {
            List<CartDetail> cartDetailList = cartNonUserService.getBookFromCart(cartCookie);
            model.addAttribute(cartDetailList);
        }
        return "cart";
    }

    /**
     * Delete item from cart string.
     *
     * @param cartCookie the cart cookie
     * @param response   the response
     * @param request    the request
     * @param bookId     the book id
     * @return the string
     */
    @GetMapping("/cart/delete")
    public String deleteItemFromCart(@CookieValue(name = CART_COOKIE_VALUE, required = false) Cookie cartCookie,
                                     HttpServletResponse response, HttpServletRequest request,
                                     @RequestParam Long bookId) {
        if (isUser(request)) {
            cartUserService.deleteBookFromCart(bookId);
        } else {
            cartNonUserService.deleteBookFromCart(cartCookie, response, bookId);
        }
        return "redirect:/cart";
    }

    /**
     * Add book to cart string.
     *
     * @param cartCookie          the cart cookie
     * @param response            the response
     * @param request             the request
     * @param cartRegisterRequest the cart register request
     * @return the string
     */
    @PostMapping("/cart/add")
    public String addBookToCart(@CookieValue(name = CART_COOKIE_VALUE, required = false) Cookie cartCookie,
                                HttpServletResponse response, HttpServletRequest request,
                                @ModelAttribute CartRegisterRequest cartRegisterRequest) {

        if (isUser(request)) {
            cartUserService.addBookToCart(cartRegisterRequest);
        } else {
            cartNonUserService.registerBookToCart(cartCookie, response, cartRegisterRequest);
        }
        return "redirect:/cart";
    }

    private boolean isUser(HttpServletRequest request) {
        return Objects.nonNull(CookieUtils.getIdentityCookieValue(request));
    }

    @PostMapping("/cart/update")
    public ResponseEntity<Void> updateBookAmount(@CookieValue(name = CartUtil.CART_COOKIE, required = false) Cookie cartCookie,
                                                 HttpServletResponse response, HttpServletRequest request,
                                                 @RequestBody List<OrderItemRequest> orderItemRequest) {
        if (isUser(request)) {
            cartUserService.updateAmountBookInCart(orderItemRequest);
        } else {
            cartNonUserService.orderBookInCart(cartCookie, response, orderItemRequest);
        }
        return ResponseEntity.ok().build();
    }
}