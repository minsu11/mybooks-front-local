package store.mybooks.front.order.controller;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
                                      ModelMap modelMap) {
        BookStockResponse bookStockResponse = bookService.getBookStockResponse(request.getId());
        orderInfoCheckService.checkAmount(request.getQuantity(), bookStockResponse.getStock());

        BookGetResponseForOrder bookGetResponseForOrder = bookService.getBookForOrder(request.getId());
        List<BookGetResponseForOrder> bookGetResponseForOrders = List.of(bookService.getBookForOrder(request.getId()));
        PointResponse pointResponse = userPointService.getPointsHeld();
        UserGetResponse user = userAdaptor.findUser();
        Integer totalCost = bookGetResponseForOrder.getSaleCost() * request.getQuantity();
        DeliveryRuleResponse deliveryRule = deliveryRuleService.getDeliveryRuleResponseByName("배송 비");
        modelMap.put("quantity", request.getQuantity());
        modelMap.put("bookLists", bookGetResponseForOrders);
        modelMap.put("totalCost", totalCost);
        modelMap.put("point", pointResponse.getRemainingPoint());
        modelMap.put("direct", true);
        modelMap.put("localDate", LocalDate.now());
        modelMap.put("user", user);
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
    public String viewOrderPage(ModelMap modelMap) {
        PointResponse pointResponse = userPointService.getPointsHeld();
        UserGetResponse user = userAdaptor.findUser();

        List<CartDetail> bookFromCart = cartUserService.getBookFromCart();

        DeliveryRuleResponse deliveryRule = deliveryRuleService.getDeliveryRuleResponseByName("배송 비");
        modelMap.put("bookLists", bookFromCart);
        modelMap.put("totalCost", orderService.calculateTotalCost(bookFromCart));
        modelMap.put("point", pointResponse.getRemainingPoint());
        modelMap.put("localDate", LocalDate.now());
        modelMap.put("user", user);
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
        BookOrderCreateResponse response = orderService.createOrder(orderRequest.getBookInfoList(),
                orderRequest.getOrderInfo(), point, couponCost, wrapCost, totalCost);
        return "redirect:/pay/" + response.getNumber();
    }

    @PostMapping("/direct/order")
    public String doDirectOrder(@ModelAttribute BookOrderRequest orderRequest
    ) {
        log.debug("값이 제대로 들어왔는지 확인: {}", orderRequest);

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
        BookOrderCreateResponse response = orderService.createOrder(orderRequest.getBookInfoList(),
                orderRequest.getOrderInfo(), point, couponCost, wrapCost, totalCost);

        return "redirect:/pay/" + response.getNumber();
    }
}
