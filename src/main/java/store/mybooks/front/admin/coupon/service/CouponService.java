package store.mybooks.front.admin.coupon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.coupon.adaptor.CouponAdaptor;
import store.mybooks.front.admin.coupon.model.request.CouponCreateRequest;
import store.mybooks.front.admin.coupon.model.response.CouponGetResponse;
import store.mybooks.front.pageable.dto.response.PageResponse;

/**
 * packageName    : store.mybooks.front.admin.coupon.service
 * fileName       : CouponService
 * author         : damho-lee
 * date           : 3/1/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/1/24          damho-lee          최초 생성
 */
@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponAdaptor couponAdaptor;

    public PageResponse<CouponGetResponse> getCoupons(Pageable pageable) {
        return couponAdaptor.getCouponPage(pageable);
    }

    public void createCoupon(CouponCreateRequest createRequest) {
        couponAdaptor.createCoupon(createRequest);
    }

    public void deleteCoupon(Long id) {
        couponAdaptor.deleteCoupon(id);
    }
}
