package store.mybooks.front.order.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import store.mybooks.front.admin.book.model.response.BookGetResponseForOrder;
import store.mybooks.front.admin.book.model.response.BookStockResponse;
import store.mybooks.front.admin.delivery_rule.dto.DeliveryRuleResponse;
import store.mybooks.front.admin.delivery_rule.service.DeliveryRuleService;
import store.mybooks.front.admin.wrap.dto.response.WrapResponse;
import store.mybooks.front.admin.wrap.service.WrapService;
import store.mybooks.front.book.service.BookService;
import store.mybooks.front.cart.domain.CartDetail;
import store.mybooks.front.cart.service.CartNonUserService;
import store.mybooks.front.cart.service.CartUserService;
import store.mybooks.front.order.dto.request.BookOrderDirectRequest;
import store.mybooks.front.order.dto.request.BookOrderRequest;
import store.mybooks.front.order.dto.response.BookOrderCreateResponse;
import store.mybooks.front.order.service.OrderInfoCheckService;
import store.mybooks.front.order.service.OrderService;
import store.mybooks.front.user.adaptor.UserAdaptor;
import store.mybooks.front.user.dto.response.UserGetResponse;
import store.mybooks.front.user_address.adaptor.UserAddressAdaptor;
import store.mybooks.front.user_address.request.UserAddressCreateRequest;
import store.mybooks.front.user_address.response.UserAddressGetResponse;
import store.mybooks.front.user_coupon.model.response.UserCouponGetResponseForOrder;
import store.mybooks.front.user_coupon.service.UserCouponService;
import store.mybooks.front.userpoint.dto.response.PointResponse;
import store.mybooks.front.userpoint.service.UserPointService;
import store.mybooks.front.utils.CookieUtils;

/**
 * packageName    : store.mybooks.front.order.controller<br>
 * fileName       : OrderController<br>
 * author         : minsu11<br>
 * date           : 3/1/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/1/24        minsu11       최초 생성<br>
 */

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {
    private final WrapService wrapService;
    private final UserAddressAdaptor userAddressAdaptor;
    private final UserAdaptor userAdaptor;
    private final OrderService orderService;
    private final UserPointService userPointService;
    private final UserCouponService userCouponService;
    private final CartNonUserService cartNonUserService;
    private final CartUserService cartUserService;
    private final OrderInfoCheckService orderInfoCheckService;
    private final DeliveryRuleService deliveryRuleService;
    private final BookService bookService;

    private static final String USER_COOKIE_VALUE = "identity_cookie";
    private static final String NON_USER_CART_VALUE = "cart";
    private static final String REDIRECT_PAY_URL = "redirect:/pay/";

    /**
     * methodName : viewOrderPage<br>
     * author : minsu11<br>
     * description : 바로 구매.
     * <br> *
     *
     * @param modelMap model
     * @return string
     */
    @GetMapping("/direct/checkout")
    public String viewDirectOrderPage(@ModelAttribute BookOrderDirectRequest request,
                                      ModelMap modelMap,
                                      HttpServletRequest httpServletRequest) {
        BookStockResponse bookStockResponse = bookService.getBookStockResponse(request.getId());
        orderInfoCheckService.checkAmount(request.getQuantity(), bookStockResponse.getStock());

        BookGetResponseForOrder bookGetResponseForOrder = bookService.getBookForOrder(request.getId());
        List<BookGetResponseForOrder> bookGetResponseForOrders = List.of(bookService.getBookForOrder(request.getId()));
        if (Objects.nonNull(CookieUtils.getIdentityCookieValue(httpServletRequest))) {
            PointResponse pointResponse = userPointService.getPointsHeld();
            UserGetResponse user = userAdaptor.findUser();
            modelMap.put("point", pointResponse.getRemainingPoint());
            modelMap.put("user", user);
            modelMap.put("check", true);
        } else {
            modelMap.put("check", false);
        }

        Integer totalCost = bookGetResponseForOrder.getSaleCost() * request.getQuantity();
        DeliveryRuleResponse deliveryRule = deliveryRuleService.getDeliveryRuleResponseByName("배송 비");
        modelMap.put("quantity", request.getQuantity());
        modelMap.put("bookLists", bookGetResponseForOrders);
        modelMap.put("totalCost", totalCost);
        modelMap.put("direct", true);
        modelMap.put("localDate", LocalDate.now());
        modelMap.put("delivery", deliveryRule);
        return "direct-order-checkout";
    }


    /**
     * methodName : viewCheckAddress<br>
     * author : minsu11<br>
     * description : 회원의 주소 목록만 나오는 view.
     * <br>
     *
     * @param modelMap model
     * @return string
     */
    @GetMapping("/address")
    public String viewCheckAddress(ModelMap modelMap) {
        List<UserAddressGetResponse> list = userAddressAdaptor.findAllUserAddress();

        modelMap.put("userAddressList", list);

        return "mini-address";
    }

    @PostMapping("/cart/address/create")
    public String createUserAddress(@ModelAttribute UserAddressCreateRequest userAddressCreateRequest) {
        userAddressAdaptor.createUserAddress(userAddressCreateRequest);
        return "redirect:/address";
    }

    /**
     * methodName : viewCheckOutWrap<br>
     * author : minsu11<br>
     * description : 포장 선택 창.
     * <br> *
     *
     * @param modelMap model
     * @return string
     */
    @GetMapping("/checkout/wraps/{id}")
    public String viewCheckOutWrap(ModelMap modelMap,
                                   @PathVariable String id) {
        List<WrapResponse> wrapResponses = wrapService.getWrapResponse();
        modelMap.put("wrapList", wrapResponses);
        modelMap.put("id", id);
        return "wrap-list-view";
    }

    /**
     * methodName : viewCoupon<br>
     * author : minsu11<br>
     * description : 주문 페이지에서 사용 가능한 쿠폰 페이지.
     * <br>
     *
     * @param modelMap model
     * @param bookId   쿠폰 적용할 도서 아이디
     * @return string
     */
    @GetMapping("/checkout/{bookId}/coupon/{id}")
    public String viewCoupon(ModelMap modelMap,
                             @PathVariable(name = "bookId") Long bookId,
                             @PathVariable(name = "id") Long id) {
        log.info("coupon value: {}", bookId);

        List<UserCouponGetResponseForOrder> useCoupon = userCouponService.getUsableUserCoupon(bookId);
        log.info("coupon id value :{}", useCoupon.toString());
        modelMap.put("couponList", useCoupon);
        modelMap.put("id", id);
        return "checkout-coupon";
    }


    /**
     * methodName : viewCoupon<br>
     * author : minsu11<br>
     * description : 장바구니를 통한 주문.
     *
     * @param modelMap the model map
     * @return the string
     */
    @GetMapping("/cart/checkout")
    public String viewOrderPage(ModelMap modelMap,
                                @CookieValue(name = NON_USER_CART_VALUE, required = false) Cookie cartCookie,
                                HttpServletRequest request) {

        List<CartDetail> bookFromCart;
        if (Objects.nonNull(CookieUtils.getIdentityCookieValue(request))) {
            PointResponse pointResponse = userPointService.getPointsHeld();
            UserGetResponse user = userAdaptor.findUser();
            modelMap.put("user", user);
            modelMap.put("point", pointResponse.getRemainingPoint());
            bookFromCart = cartUserService.getBookFromCart();
            modelMap.put("userCheck", true);
        } else {

            bookFromCart = cartNonUserService.getBookFromCart(cartCookie);
            orderInfoCheckService.validationCheckNonUserOrder(bookFromCart);
            modelMap.put("userCheck", false);
        }

        log.debug("카트 구매: {}", bookFromCart);
        DeliveryRuleResponse deliveryRule = deliveryRuleService.getDeliveryRuleResponseByName("배송 비");
        modelMap.put("bookLists", bookFromCart);
        modelMap.put("totalCost", orderService.calculateTotalCost(bookFromCart));
        modelMap.put("localDate", LocalDate.now());
        modelMap.put("delivery", deliveryRule);
        return "checkout";
    }

    /**
     * methodName : doOrder<br>
     * author : minsu11<br>
     * description : 주문 처리.
     *
     * @param orderRequest the order request
     * @return the string
     */
    @PostMapping("/order")
    public String doOrder(@ModelAttribute BookOrderRequest orderRequest) {
        List<CartDetail> cart = cartUserService.getBookFromCart();
        orderInfoCheckService.checkModulation(orderRequest, cart);
        int point = orderService.getPoint(orderRequest.getOrderInfo());
        int couponCost = orderService.calculateBookCouponCost(orderRequest.getBookInfoList());
        int wrapCost = orderService.calculateBookWrapCost(orderRequest.getBookInfoList());
        int totalCost = orderService.calculateTotalCost(cart);
        BookOrderCreateResponse response = orderService.createOrder(
                orderRequest.getUserInfo(), orderRequest.getBookInfoList(), orderRequest.getOrderInfo(),
                point, couponCost, wrapCost, totalCost);
        return REDIRECT_PAY_URL + response.getNumber();
    }

    /**
     * 비회원 주문.
     *
     * @param orderRequest the order request
     * @param cookie       the cookie
     * @return the string
     */
    @PostMapping("/cart/order/non/user")
    public String doNonUserOrder(@ModelAttribute BookOrderRequest orderRequest,
                                 @CookieValue(name = NON_USER_CART_VALUE, required = false) Cookie cookie) {
        List<CartDetail> cart = cartNonUserService.getBookFromCart(cookie);
        orderInfoCheckService.checkNonOrderModulation(orderRequest, cart);
        int wrapCost = orderService.calculateBookWrapCost(orderRequest.getBookInfoList());
        int totalCost = orderService.calculateTotalCost(cart);
        BookOrderCreateResponse response = orderService.createNonUserOrder(orderRequest, wrapCost, totalCost);
        return REDIRECT_PAY_URL + response.getNumber();
    }

    @PostMapping("/direct/order")
    public String doDirectOrder(@ModelAttribute BookOrderRequest orderRequest
    ) {
        BookGetResponseForOrder bookGetResponseForOrder =
                bookService.getBookForOrder(orderRequest.getBookInfoList().get(0).getBookId());

        orderInfoCheckService.checkDirectOrderModulation(orderRequest, bookGetResponseForOrder);

        int point = orderRequest.getOrderInfo().getUsingPoint(); // 사용한 포인트
        log.debug("point cost: {}", point);
        // coupon cost
        int couponCost = orderService.calculateBookCouponCost(orderRequest.getBookInfoList());
        log.debug("coupon cost: {}", couponCost);
        int wrapCost = orderService.calculateBookWrapCost(orderRequest.getBookInfoList());
        log.debug("wrap cost: {}", wrapCost);

        int totalCost = bookGetResponseForOrder.getSaleCost()
                * orderRequest.getBookInfoList().get(0).getAmount();
        BookOrderCreateResponse response = orderService.createOrder(
                orderRequest.getUserInfo(),
                orderRequest.getBookInfoList(),
                orderRequest.getOrderInfo(), point, couponCost, wrapCost, totalCost);

        return REDIRECT_PAY_URL + response.getNumber();
    }

    /**
     * 결제가 성공된 장바구니 삭제.
     *
     * @return the response entity
     */
    @GetMapping("/cart/info")
    public ResponseEntity<Void> removeCart(@CookieValue(name = NON_USER_CART_VALUE, required = false) Cookie cookie,
                                           HttpServletRequest request,
                                           HttpServletResponse response) {
        if (Objects.nonNull(CookieUtils.getIdentityCookieValue(request))) {
            cartUserService.deleteAllBookFromCart();
        } else {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/order")
    public String getAllUserOrder(@PageableDefault Pageable pageable, Model model) {

        model.addAttribute("orders", orderService.getAllUserBookOrder(pageable));
        return "user-order";
    }


    /**
     * methodName : deleteAddress<br>
     * author : minsu11<br>
     * description : 배송지에서 유저의 주소를 삭제.
     *
     * @param addressId id
     * @return string
     */
    @PostMapping("/address/delete")
    public String deleteAddress(@RequestParam(name = "addressId") Long addressId) {
        userAddressAdaptor.deleteUserAddress(addressId);
        return "redirect:/address";
    }

}
