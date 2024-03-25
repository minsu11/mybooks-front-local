package store.mybooks.front.order.service;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.book.model.response.BookGetResponseForOrder;
import store.mybooks.front.admin.book.model.response.BookStockResponse;
import store.mybooks.front.admin.wrap.adaptor.WrapAdaptor;
import store.mybooks.front.book.adaptor.BookAdaptor;
import store.mybooks.front.cart.domain.CartDetail;
import store.mybooks.front.order.adaptor.OrderAdaptor;
import store.mybooks.front.order.dto.request.BookInfoRequest;
import store.mybooks.front.order.dto.request.BookOrderRequest;
import store.mybooks.front.order.dto.request.OrderInfoRequest;
import store.mybooks.front.order.dto.request.OrderUserInfoRequest;
import store.mybooks.front.order.dto.response.BookOrderDetailResponse;
import store.mybooks.front.order.exception.AmountNegativeException;
import store.mybooks.front.order.exception.AmountOverStockException;
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
@Slf4j
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
     * description : 장바구니 구매 과정에서 {@code html} 변조 데이터  검사.
     * <br>
     *
     * @param bookOrderRequest 주문서에서 작성한 데이터
     * @param cartInfo         장바구니에 담긴 데이터
     */
    public void checkModulation(BookOrderRequest bookOrderRequest, List<CartDetail> cartInfo) {
        OrderInfoRequest orderInfoRequest = bookOrderRequest.getOrderInfo();
        List<BookInfoRequest> bookInfoRequest = bookOrderRequest.getBookInfoList();
        checkOrderAddress(bookOrderRequest.getUserInfo());
        checkDuplicateCoupon(bookInfoRequest);
        checkPoint(orderInfoRequest.getUsingPoint());
        checkBookInfos(bookInfoRequest, cartInfo);

    }

    public void checkNonOrderModulation(BookOrderRequest bookOrderRequest, List<CartDetail> cartInfo) {
        List<BookInfoRequest> bookInfoRequest = bookOrderRequest.getBookInfoList();
        checkBookInfos(bookInfoRequest, cartInfo);
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
    public void checkBookInfos(List<BookInfoRequest> bookInfos, List<CartDetail> cartInfos) {
        if (bookInfos.size() != cartInfos.size()) {
            throw new OrderInfoNotMatchException("장바구니에 담긴 물품의 갯수가 다름");
        }
        for (int i = 0; i < bookInfos.size(); i++) {
            BookInfoRequest bookInfo = bookInfos.get(i);
            CartDetail cartInfo = cartInfos.get(i);
            if (
                    !Objects.equals(bookInfo.getBookId(), cartInfo.getBookId())
                            || !Objects.equals(bookInfo.getSaleCost(), cartInfo.getSaleCost())
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
                if (Objects.isNull(bookInfos.get(i).getSelectCouponId())) {
                    break;
                }

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
            if (Objects.equals(couponList.get(j).getUserCouponId(), bookInfo.getSelectCouponId())) {
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
     * @param userInfoRequest 주문 정보가 담긴 DTO
     */
    public void checkOrderAddress(OrderUserInfoRequest userInfoRequest) {
        Long id = userInfoRequest.getAddressId();
        orderAdapter.checkOrderUserAddressInfo(id);
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

    /**
     * methodName : checkDirectOrderModulation<br>
     * author : minsu11<br>
     * description : 바로 구매 과정에서 {@code html} 변조 데이터 검사
     * <br>
     *
     * @param bookOrderRequest        주문서에 작성한 데이터
     * @param bookGetResponseForOrder {@code DB}에서 조회한 데이터
     */
    public void checkDirectOrderModulation(BookOrderRequest bookOrderRequest,
                                           BookGetResponseForOrder bookGetResponseForOrder) {

        OrderInfoRequest orderInfoRequest = bookOrderRequest.getOrderInfo();
        List<BookInfoRequest> bookInfoRequest = bookOrderRequest.getBookInfoList();
        checkOrderAddress(bookOrderRequest.getUserInfo());
        checkDuplicateCoupon(bookInfoRequest);
        checkPoint(orderInfoRequest.getUsingPoint());
        isCheckAmount(bookInfoRequest, bookGetResponseForOrder);
    }


    public void isCheckAmount(List<BookInfoRequest> bookInfoRequest, BookGetResponseForOrder bookGetResponseForOrder) {
        checkAmount(bookInfoRequest.get(0).getAmount(), bookGetResponseForOrder.getStock());
    }

    /**
     * 장바구니 구매과정에서 재고 검사.
     *
     * @param bookOrderDetailList the cart details
     */
    public void isCheckAmountBookCart(List<BookOrderDetailResponse> bookOrderDetailList) {
        for (BookOrderDetailResponse bookOrderDetail : bookOrderDetailList) {
            BookStockResponse bookStockResponse = bookAdaptor.getBookStockResponse(bookOrderDetail.getId());
            if (bookOrderDetail.getAmount() > bookStockResponse.getStock()) {
                log.info("주문 재고: {}", bookOrderDetail.getAmount());
                log.info("도서 재고: {}", bookStockResponse.getStock());
                throw new AmountOverStockException();
            }
        }
    }

    public void checkAmount(int amount, int equalsAmount) {
        if (amount <= 0) {
            throw new AmountNegativeException();
        }

        if (amount > equalsAmount) {
            throw new AmountOverStockException();
        }
    }

    public void validationCheckNonUserOrder(List<CartDetail> cartDetailList) {
        for (CartDetail cartDetail : cartDetailList) {
            BookStockResponse stock = bookAdaptor.getBookStockResponse(cartDetail.getBookId());
            if (cartDetail.getStock() > stock.getStock()) {
                throw new AmountOverStockException();
            }
            if (cartDetail.getStock() <= 0) {
                throw new AmountNegativeException();
            }
        }

    }

}
