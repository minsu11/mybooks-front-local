package store.mybooks.front.order.service;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.wrap.adaptor.WrapAdaptor;
import store.mybooks.front.book.adaptor.BookAdaptor;
import store.mybooks.front.cart.domain.CartDetail;
import store.mybooks.front.order.adaptor.OrderAdaptor;
import store.mybooks.front.order.dto.request.BookInfoRequest;
import store.mybooks.front.order.dto.request.BookOrderRequest;
import store.mybooks.front.order.dto.request.OrderInfoRequest;
import store.mybooks.front.order.exception.OrderInfoNotMatchException;
import store.mybooks.front.user_address.adaptor.UserAddressAdaptor;
import store.mybooks.front.user_coupon.adaptor.UserCouponAdaptor;
import store.mybooks.front.user_coupon.model.response.UserCouponGetResponseForOrder;
import store.mybooks.front.userpoint.adaptor.UserPointAdaptor;
import store.mybooks.front.userpoint.dto.response.PointResponse;

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
public class OrderInfoCheckService {
    private final BookAdaptor bookAdaptor;
    private final OrderAdaptor orderAdapter;
    private final UserAddressAdaptor userAddressAdaptor;
    private final WrapAdaptor wrapAdaptor;
    private final UserCouponAdaptor userCouponAdaptor;
    private final UserPointAdaptor userPointAdaptor;


    /**
     * methodName : checkModulation<br>
     * author : minsu11<br>
     * description : {@Code html}에서 변조 데이터 들어왔는지 검사.
     * <br>
     *
     * @param bookOrderRequest the book order request
     * @param cartInfo         the cart info
     */
    public void checkModulation(BookOrderRequest bookOrderRequest, List<CartDetail> cartInfo) {
        OrderInfoRequest orderInfoRequest = bookOrderRequest.getOrderInfo();
        List<BookInfoRequest> bookInfoRequest = bookOrderRequest.getBookInfoList();
        checkOrderInfo(orderInfoRequest);
        checkDuplicateCoupon(bookInfoRequest);
        checkBookInfo(bookInfoRequest, cartInfo);

    }

    /**
     * methodName : checkBookInfo<br>
     * author : minsu11<br>
     * description : 장바구니에 담긴 정보랑 주문할 도서 정보가 같은지 확인.
     * <br> *
     *
     * @param bookInfos 주문할 도서 정보
     * @param cartInfos 장바구니 정보
     */
//todo 수량 체크하는 부분 추가해야함
    public void checkBookInfo(List<BookInfoRequest> bookInfos, List<CartDetail> cartInfos) {
        if (bookInfos.size() == cartInfos.size()) {
            throw new OrderInfoNotMatchException("장바구니에 담긴 물품의 갯수가 다름");
        }
        for (int i = 0; i < bookInfos.size(); i++) {
            BookInfoRequest bookInfo = bookInfos.get(i);
            CartDetail cartInfo = cartInfos.get(i);
            if (
                    !bookInfo.getBookId().equals(cartInfo.getBookId())
                            || !bookInfo.getSaleCost().equals(cartInfo.getSaleCost())
                            || !Objects.equals(bookInfo.getAmount(), cartInfo.getCartDetailAmount())
            ) {
                throw new OrderInfoNotMatchException("도서의 정보가 다름");
            }
            if (Objects.nonNull(bookInfo.getSelectWrapId())) {
                wrapAdaptor.getWrap(bookInfo.getSelectWrapId());
            }
            if (Objects.nonNull(bookInfo.getSelectCouponId())) {
                checkBookCoupon(bookInfo);
            }
        }
    }

    /**
     * methodName : checkDuplicateCoupon<br>
     * author : minsu11<br>
     * description : 같은 쿠폰이 있는지 확인.
     * <br> *
     *
     * @param bookInfos the book infos
     */
    public void checkDuplicateCoupon(List<BookInfoRequest> bookInfos) {
        for (int i = 0; i < bookInfos.size(); i++) {
            for (int j = i + 1; j < bookInfos.size(); j++) {
                if (Objects.equals(bookInfos.get(i).getSelectCouponId(), bookInfos.get(j).getSelectCouponId())) {
                    throw new OrderInfoNotMatchException("같은 쿠폰이 입력됨");
                }
            }
        }
    }

    /**
     * methodName : checkBookCoupon<br>
     * author : minsu11<br>
     * description : 책에 적용된 쿠폰 검사. 본인이 소유한 쿠폰이 아니면 {@cod throw}를 던짐.
     * <br> *
     *
     * @param bookInfo 도서관련 DTO
     */
    public void checkBookCoupon(BookInfoRequest bookInfo) {
        boolean isCheck = false;
        List<UserCouponGetResponseForOrder> couponList = userCouponAdaptor.getUsableUserCoupon(bookInfo.getBookId());
        for (int j = 0; j < couponList.size(); j++) {
            if (Objects.equals(couponList.get(j), bookInfo.getSelectCouponId())) {
                isCheck = true;
            }
        }
        if (!isCheck) {
            throw new OrderInfoNotMatchException("쿠폰 정보 다름");
        }
    }

    /**
     * methodName : checkOrderInfo<br>
     * author : minsu11<br>
     * description : 회원의 주문 정보를 검사하는 메서드.
     * <br> *
     *
     * @param orderInfoRequest 주문 정보가 담긴 DTO
     */
    public void checkOrderInfo(OrderInfoRequest orderInfoRequest) {
        orderAdapter.checkOrderUserAddressInfo(orderInfoRequest.getAddressId());
        checkPoint(orderInfoRequest.getUsingPoint());
    }

    /**
     * methodName : checkPoint<br>
     * author : minsu11<br>
     * description : 회원이 가진 포인트 검사. DB에 등록된 회원의 포인트 보다 높으면
     * {@code OrderInfoNotMatchException}을 던짐.
     * <br> *
     *
     * @param usingPoint 사용할 포인트
     */
    public void checkPoint(Integer usingPoint) {
        PointResponse pointsHeld = userPointAdaptor.getPointsHeld();
        if (usingPoint < 0 || pointsHeld.getRemainingPoint() < usingPoint) {
            throw new OrderInfoNotMatchException("포인트 정보가 다름");
        }
    }
}
