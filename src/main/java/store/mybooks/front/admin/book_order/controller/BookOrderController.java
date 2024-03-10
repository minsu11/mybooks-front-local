package store.mybooks.front.admin.book_order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import store.mybooks.front.admin.book_order.dto.request.BookOrderRegisterInvoiceNumberRequest;
import store.mybooks.front.admin.book_order.dto.request.BookOrderStatusModifyRequest;
import store.mybooks.front.admin.book_order.dto.response.BookOrderAdminResponse;
import store.mybooks.front.admin.book_order.service.BookOrderService;
import store.mybooks.front.pageable.dto.response.PageResponse;

/**
 * packageName    : store.mybooks.front.admin.book_order.controller<br>
 * fileName       : BookOrderController<br>
 * author         : minsu11<br>
 * date           : 3/3/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/3/24        minsu11       최초 생성<br>
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/order")
public class BookOrderController {
    private final BookOrderService bookOrderService;


    /**
     * methodName : viewBookOrder<br>
     * author : minsu11<br>
     * description : 관리자 주문 내역.
     * <br> *
     *
     * @param modelMap model
     * @param pageable page
     * @return string
     */
    @GetMapping
    public String viewBookOrder(ModelMap modelMap, Pageable pageable) {
        PageResponse<BookOrderAdminResponse> bookOrderAdminPage = bookOrderService.getBookOrderAdminPage(pageable);
        modelMap.put("bookOrderList", bookOrderAdminPage);
        return "admin/view/book-order/order-view";
    }

    /**
     * methodName : doModifyStatus<br>
     * author : minsu11<br>
     * description : 주문 상태 변경.
     * <br> *
     *
     * @param request
     * @return string
     */
    @PostMapping("/status")
    public String doModifyStatus(@ModelAttribute BookOrderStatusModifyRequest request) {
        bookOrderService.modifyAdminOrderStatus(request);
        return "redirect:/admin/order";
    }

    @GetMapping("/{id}")
    public String viewInvoiceNumberRegisterView(@PathVariable Long id, ModelMap modelMap) {
        modelMap.put("id", id);
        return "admin/view/book-order/invoiceNumber-register-view";
    }

    /**
     * methodName : doRegisterInvoiceNumber<br>
     * author : minsu11<br>
     * description : 주문 상태의 송장 번호 등록.
     * <br> *
     *
     * @param request 등록할 송장 번호 DTO
     * @return string
     */
    @PostMapping("/invoiceNumber")
    public String doRegisterInvoiceNumber(@ModelAttribute BookOrderRegisterInvoiceNumberRequest request) {
        bookOrderService.registerInvoiceNumber(request);
        return "redirect:/admin/order";
    }


}
