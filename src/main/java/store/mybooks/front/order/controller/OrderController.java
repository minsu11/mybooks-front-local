package store.mybooks.front.order.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import store.mybooks.front.admin.book.model.response.BookDetailResponse;
import store.mybooks.front.admin.wrap.dto.response.WrapResponse;
import store.mybooks.front.admin.wrap.service.WrapService;
import store.mybooks.front.order.dto.request.BookOrderDirectRequest;
import store.mybooks.front.order.service.OrderService;
import store.mybooks.front.user.adaptor.UserAdaptor;
import store.mybooks.front.user.dto.response.UserGetResponse;
import store.mybooks.front.user_address.adaptor.UserAddressAdaptor;
import store.mybooks.front.user_address.response.UserAddressGetResponse;

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


    /**
     * methodName : viewOrderPage<br>
     * author : minsu11<br>
     * description : 바로 구매.
     * <br> *
     *
     * @param model map
     * @return string
     */
    @GetMapping("/direct/checkout")
    public String viewOrderPage(@ModelAttribute BookOrderDirectRequest request,
                                ModelMap modelMap) {
        List<WrapResponse> wrapResponses = wrapService.getWrapResponse();
        BookDetailResponse bookDetailResponse = orderService.getBook(request);
        log.info("request id:{}", request.getId());
        log.info("request id:{}", request.getSaleCost());
        log.info("request id:{}", request.getQuantity());
        UserGetResponse user = userAdaptor.findUser();
        Integer totalCost = bookDetailResponse.getSaleCost() * request.getQuantity();
        modelMap.put("book", bookDetailResponse);
        modelMap.put("totalCost", totalCost);
        modelMap.put("wrapList", wrapResponses);
        modelMap.put("user", user);
        modelMap.put("quantity", request.getQuantity());

        return "checkout";
    }

    /**
     * methodName : viewCheckAddress<br>
     * author : minsu11<br>
     * description : 회원의 주소 목록만 나오는 view.
     * <br> *
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

}
