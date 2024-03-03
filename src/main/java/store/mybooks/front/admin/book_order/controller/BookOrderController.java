package store.mybooks.front.admin.book_order.controller;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.admin.book_order.dto.request.BookOrderStatusModifyRequest;
import store.mybooks.front.admin.book_order.dto.response.BookOrderAdminResponse;
import store.mybooks.front.admin.book_order.service.BookOrderService;

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
//        Page<BookOrderAdminResponse> bookOrderAdminPage = bookOrderService.getBookOrderAdminPage(pageable);
        List<BookOrderAdminResponse> bookOrderAdminPage = List.of(getBookOrderAdminResponse());
        modelMap.put("bookOrderList", bookOrderAdminPage);
        return "admin/view/order-view";
    }

    @PostMapping("/status")
    public String doModifyStatus(@ModelAttribute BookOrderStatusModifyRequest request) {
        
    }


    private static BookOrderAdminResponse getBookOrderAdminResponse() {
        BookOrderAdminResponse bookOrderAdminResponse = new BookOrderAdminResponse();
        bookOrderAdminResponse.setUserId(1L);
        bookOrderAdminResponse.setStatusId("대기");
        bookOrderAdminResponse.setDate(LocalDate.now());
        bookOrderAdminResponse.setInvoiceNumber("124818247");
        bookOrderAdminResponse.setOutDate(null);
        bookOrderAdminResponse.setNumber("12124562456");
        return bookOrderAdminResponse;
    }
}
