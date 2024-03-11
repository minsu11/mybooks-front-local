package store.mybooks.front.user_coupon.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import store.mybooks.front.pageable.dto.response.PageResponse;
import store.mybooks.front.user_coupon.adaptor.UserCouponAdaptor;
import store.mybooks.front.user_coupon.model.response.UserCouponGetResponse;
import store.mybooks.front.user_coupon.model.response.UserCouponGetResponseForOrder;

/**
 * packageName    : store.mybooks.front.user_coupon.service
 * fileName       : UserCouponService
 * author         : damho-lee
 * date           : 3/5/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/5/24          damho-lee          최초 생성
 */
@Service
@RequiredArgsConstructor
public class UserCouponService {
    private final UserCouponAdaptor userCouponAdaptor;

    /**
     * methodName : getUserCoupons <br>
     * author : damho-lee <br>
     * description : 마이페이지 회원 쿠폰함에서 사용할 조회 메서드.<br>
     *
     * @param pageable Pageable
     * @return page response
     */
    public PageResponse<UserCouponGetResponse> getUserCoupons(Pageable pageable) {
        return userCouponAdaptor.getUserCoupons(pageable);
    }

    /**
     * methodName : getUsableUserCoupon <br>
     * author : damho-lee <br>
     * description : 도서에 적용할 수 있는 회원 쿠폰 리스트 조회.<br>
     *
     * @param bookId Long
     * @return list
     */
    public List<UserCouponGetResponseForOrder> getUsableUserCoupon(Long bookId) {
        return userCouponAdaptor.getUsableUserCoupon(bookId);
    }

    /**
     * methodName : getUsableUserTotalCoupon <br>
     * author : damho-lee <br>
     * description : 주문 전체에 적용할 수 있는 전체 쿠폰 리스트 조회.<br>
     *
     * @return list
     */
    public List<UserCouponGetResponseForOrder> getUsableUserTotalCoupon() {
        return userCouponAdaptor.getUsableUserTotalCoupon();
    }
}
