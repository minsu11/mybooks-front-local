package store.mybooks.front.user_coupon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import store.mybooks.front.pageable.dto.response.PageResponse;
import store.mybooks.front.user_coupon.adaptor.UserCouponAdaptor;
import store.mybooks.front.user_coupon.model.response.UserCouponGetResponse;

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

    public PageResponse<UserCouponGetResponse> getUserCoupons(Pageable pageable) {
        return userCouponAdaptor.getUserCoupons(pageable);
    }
}
