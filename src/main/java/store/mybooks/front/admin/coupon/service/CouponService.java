package store.mybooks.front.admin.coupon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.coupon.adaptor.CouponAdaptor;
import store.mybooks.front.admin.coupon.model.request.BookFlatDiscountCouponCreateRequest;
import store.mybooks.front.admin.coupon.model.request.BookPercentageCouponCreateRequest;
import store.mybooks.front.admin.coupon.model.request.CategoryFlatDiscountCouponCreateRequest;
import store.mybooks.front.admin.coupon.model.request.CategoryPercentageCouponCreateRequest;
import store.mybooks.front.admin.coupon.model.request.FlatDiscountCouponCreateRequest;
import store.mybooks.front.admin.coupon.model.request.TotalPercentageCouponCreateRequest;

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

    public void createTotalPercentageCoupon(TotalPercentageCouponCreateRequest createRequest) {
        couponAdaptor.createTotalPercentageCoupon(createRequest);
    }

    public void createFlatDiscountCoupon(FlatDiscountCouponCreateRequest createRequest) {
        couponAdaptor.createFlatDiscountCoupon(createRequest);
    }

    public void createBookPercentageCoupon(BookPercentageCouponCreateRequest createRequest) {
        couponAdaptor.createBookPercentageCoupon(createRequest);
    }

    public void createBookFlatDiscountCouponCreateRequest(BookFlatDiscountCouponCreateRequest createRequest) {
        couponAdaptor.createBookFlatDiscountCoupon(createRequest);
    }

    public void createCategoryPercentageCoupon(CategoryPercentageCouponCreateRequest createRequest) {
        couponAdaptor.createCategoryPercentageCoupon(createRequest);
    }

    public void createCategoryFlatDiscountCouponCreateRequest(CategoryFlatDiscountCouponCreateRequest createRequest) {
        couponAdaptor.createCategoryFlatDiscountCoupon(createRequest);
    }
}
