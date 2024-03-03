package store.mybooks.front.admin.book_order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.book_order.adaptor.BookOrderAdaptor;
import store.mybooks.front.admin.book_order.dto.request.BookOrderStatusModifyRequest;
import store.mybooks.front.admin.book_order.dto.response.BookOrderAdminResponse;

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
    public Page<BookOrderAdminResponse> getBookOrderAdminPage(Pageable pageable) {
        return bookOrderAdaptor.getAdminBookOrderList(pageable);
    }

    public void modifyAdminOrderStatus(BookOrderStatusModifyRequest request) {
        bookOrderAdaptor.modifyOrderStatus(request);
    }


}
