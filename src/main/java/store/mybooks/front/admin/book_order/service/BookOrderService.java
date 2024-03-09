package store.mybooks.front.admin.book_order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.book_order.adaptor.BookOrderAdaptor;
import store.mybooks.front.admin.book_order.dto.request.BookOrderRegisterInvoiceNumberRequest;
import store.mybooks.front.admin.book_order.dto.request.BookOrderStatusModifyRequest;
import store.mybooks.front.admin.book_order.dto.response.BookOrderAdminResponse;
import store.mybooks.front.pageable.dto.response.PageResponse;

/**
 * packageName    : store.mybooks.front.admin.book_order.service<br>
 * fileName       : BookOrderService<br>
 * author         : minsu11<br>
 * date           : 3/3/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/3/24        minsu11       최초 생성<br>
 */
@Service
@RequiredArgsConstructor
public class BookOrderService {
    private final BookOrderAdaptor bookOrderAdaptor;

    /**
     * methodName : getBookOrderAdminPage<br>
     * author : minsu11<br>
     * description : 관리자 주문 내역.
     * <br> *
     *
     * @param pageable 페이징
     * @return page
     */
    public PageResponse<BookOrderAdminResponse> getBookOrderAdminPage(Pageable pageable) {
        return bookOrderAdaptor.getAdminBookOrderList(pageable);
    }

    /**
     * methodName : modifyAdminOrderStatus<br>
     * author : minsu11<br>
     * description : 관리자가 주문 내역 상태를 변경
     * <br> *
     *
     * @param request
     */
    public void modifyAdminOrderStatus(BookOrderStatusModifyRequest request) {

        bookOrderAdaptor.modifyOrderStatus(request);

    }

    /**
     * methodName : registerInvoiceNumber<br>
     * author : minsu11<br>
     * description : 송장 번호 등록.
     * <br> *
     *
     * @param request
     */
    public void registerInvoiceNumber(BookOrderRegisterInvoiceNumberRequest request) {
        bookOrderAdaptor.registerInvoiceNumberResponse(request);

    }

}
