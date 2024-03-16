package store.mybooks.front.order.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.book.model.response.BookDetailResponse;
import store.mybooks.front.admin.wrap.adaptor.WrapAdaptor;
import store.mybooks.front.book.adaptor.BookAdaptor;
import store.mybooks.front.cart.domain.CartDetail;
import store.mybooks.front.order.adaptor.OrderAdaptor;
import store.mybooks.front.order.dto.request.BookOrderDirectRequest;
import store.mybooks.front.user.adaptor.UserAdaptor;
import store.mybooks.front.user_address.adaptor.UserAddressAdaptor;
import store.mybooks.front.user_coupon.adaptor.UserCouponAdaptor;
import store.mybooks.front.userpoint.adaptor.UserPointAdaptor;

/**
 * packageName    : store.mybooks.front.order.service<br>
 * fileName       : OrderService<br>
 * author         : minsu11<br>
 * date           : 3/5/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/5/24        minsu11       최초 생성<br>
 */
@Service
@RequiredArgsConstructor
public class OrderService {
    private final BookAdaptor bookAdaptor;
    private final OrderAdaptor orderAdapter;
    private final UserAddressAdaptor userAddressAdaptor;
    private final UserAdaptor userAdaptor;
    private final WrapAdaptor wrapAdaptor;
    private final UserCouponAdaptor userCouponAdaptor;
    private final UserPointAdaptor userPointAdaptor;

    /**
     * methodName : getBook<br>
     * author : minsu11<br>
     * description : 주문 결제 페이지에 보일 주문내역 도서.
     * <br> *
     *
     * @param request 요청할 도서 정보
     * @return book detail response
     */
    public BookDetailResponse getBook(BookOrderDirectRequest request) {
        return bookAdaptor.getBook(request.getId());
    }

    /**
     * methodName : calculateTotalCost<br>
     * author : minsu11<br>
     * description : 도서의 총 주문 결제.
     * <br> *
     *
     * @param cartDetailList 장바구니 상세
     * @return integer
     */
    public Integer calculateTotalCost(List<CartDetail> cartDetailList) {
        int totalCost = 0;
        for (CartDetail cartDetail : cartDetailList) {
            totalCost += (cartDetail.getSaleCost() * cartDetail.getCartDetailAmount());
        }
        return totalCost;
    }

    public List<Integer> calculateBooksCost(List<CartDetail> cartDetailList) {
        return cartDetailList.stream()
                .map(cartDetail -> cartDetail.getSaleCost() * cartDetail.getCartDetailAmount())
                .collect(Collectors.toList());

    }
}
