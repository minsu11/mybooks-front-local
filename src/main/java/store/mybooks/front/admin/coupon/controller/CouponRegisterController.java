package store.mybooks.front.admin.coupon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.admin.coupon.model.request.BookFlatDiscountCouponCreateRequest;
import store.mybooks.front.admin.coupon.model.request.BookPercentageCouponCreateRequest;
import store.mybooks.front.admin.coupon.model.request.CategoryFlatDiscountCouponCreateRequest;
import store.mybooks.front.admin.coupon.model.request.CategoryPercentageCouponCreateRequest;
import store.mybooks.front.admin.coupon.model.request.FlatDiscountCouponCreateRequest;
import store.mybooks.front.admin.coupon.model.request.TotalPercentageCouponCreateRequest;
import store.mybooks.front.admin.coupon.service.CouponService;

/**
 * packageName    : store.mybooks.front.admin.coupon.controller
 * fileName       : CouponRegisterController
 * author         : damho-lee
 * date           : 3/1/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/1/24          damho-lee          최초 생성
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/coupon/register")
public class CouponRegisterController {
    private final CouponService couponService;
    private static final String CATEGORY_REGISTER_PAGE = "redirect:/admin/coupon/register";

    @PostMapping("/total-percentage-coupon")
    public String createTotalPercentageCoupon(@ModelAttribute TotalPercentageCouponCreateRequest createRequest) {
        couponService.createTotalPercentageCoupon(createRequest);
        return CATEGORY_REGISTER_PAGE;
    }

    @PostMapping("/flat-discount-coupon")
    public String createFlatDiscountCoupon(@ModelAttribute FlatDiscountCouponCreateRequest createRequest) {
        couponService.createFlatDiscountCoupon(createRequest);
        return CATEGORY_REGISTER_PAGE;
    }

    @PostMapping("/book-percentage-coupon")
    public String createBookPercentageCoupon(@ModelAttribute BookPercentageCouponCreateRequest createRequest) {
        couponService.createBookPercentageCoupon(createRequest);
        return CATEGORY_REGISTER_PAGE;
    }

    @PostMapping("/book-flat-discount-coupon")
    public String createBookFlatDiscountCoupon(@ModelAttribute BookFlatDiscountCouponCreateRequest createRequest) {
        couponService.createBookFlatDiscountCouponCreateRequest(createRequest);
        return CATEGORY_REGISTER_PAGE;
    }

    @PostMapping("/category-percentage-coupon")
    public String createCategoryPercentageCoupon(@ModelAttribute CategoryPercentageCouponCreateRequest createRequest) {
        couponService.createCategoryPercentageCoupon(createRequest);
        return CATEGORY_REGISTER_PAGE;
    }

    @PostMapping("/category-flat-discount-coupon")
    public String createCategoryFlatDiscountCoupon(
            @ModelAttribute CategoryFlatDiscountCouponCreateRequest createRequest) {
        couponService.createCategoryFlatDiscountCouponCreateRequest(createRequest);
        return CATEGORY_REGISTER_PAGE;
    }
}
