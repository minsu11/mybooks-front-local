package store.mybooks.front.payment.controller;

import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import store.mybooks.front.config.TossAppKey;
import store.mybooks.front.order.dto.response.BookOrderInfoResponse;
import store.mybooks.front.order.service.OrderService;
import store.mybooks.front.payment.dto.request.PayCancelReasonRequest;
import store.mybooks.front.payment.dto.request.TossPaymentRequest;
import store.mybooks.front.payment.dto.response.TossPaymentResponse;
import store.mybooks.front.payment.service.PayService;


/**
 * packageName    : store.mybooks.front.payment<br>
 * fileName       : WidgetController<br>
 * author         : minsu11<br>
 * date           : 3/13/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/13/24        minsu11       최초 생성<br>
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/pay")
public class PayController {
    private final TossAppKey tossAppKey;
    private final OrderService orderService;
    private final PayService payService;


    /**
     * 인증성공처리
     *
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/success")
    public String paymentRequest(@ModelAttribute TossPaymentRequest request) {
        return "success";
    }

    @GetMapping("/{orderNumber}")
    public String index(ModelMap model,
                        @PathVariable(name = "orderNumber") String orderNumber) throws Exception {
        BookOrderInfoResponse bookOrderInfoResponse = orderService.getPayBookOrderInfo(orderNumber);
        model.put("tossValue", tossAppKey.getClientKey());
        model.put("orderInfo", bookOrderInfoResponse);
        model.put("orderNumber", orderNumber);
        return "payment";
    }

    @GetMapping("/non/{orderNumber}")
    public String indexNonUser(ModelMap model,
                               @PathVariable(name = "orderNumber") String orderNumber) throws Exception {
        BookOrderInfoResponse bookOrderInfoResponse = orderService.getPayBookOrderInfo(orderNumber);
        model.put("tossValue", tossAppKey.getClientKey());
        model.put("orderInfo", bookOrderInfoResponse);
        model.put("orderNumber", orderNumber);
        return "payment";
    }

    /**
     * 인증실패처리.
     *
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/fail")
    public String failPayment(HttpServletRequest request, Model model) throws Exception {
        String failCode = request.getParameter("code");
        String failMessage = request.getParameter("message");

        model.addAttribute("code", failCode);
        model.addAttribute("message", failMessage);

        return "fail";
    }

    @GetMapping("/cancel/{orderNumber}")
    public String viewOrderCancel(@PathVariable String orderNumber,
                                  ModelMap modelMap) {
        modelMap.put("orderNumber", orderNumber);
        return "pay-cancel";
    }


    @PostMapping("/cancel/{orderNumber}")
    public String doOrderCancel(@PathVariable String orderNumber,
                                @ModelAttribute PayCancelReasonRequest request,
                                ModelMap modelMap) {
        TossPaymentResponse response = payService.cancelToss(orderNumber, request);
        payService.cancelPayAfterProcess(response);
        return "redirect:/order";
    }
}
