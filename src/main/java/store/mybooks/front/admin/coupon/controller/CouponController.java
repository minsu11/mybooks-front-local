package store.mybooks.front.admin.coupon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.admin.coupon.service.CouponService;

/**
 * packageName    : store.mybooks.front.admin.coupon
 * fileName       : CouponController
 * author         : damho-lee
 * date           : 2/29/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/29/24          damho-lee          최초 생성
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/coupon")
public class CouponController {
    private final CouponService couponService;

    @GetMapping
    public String getCategoryPage() {
        return "admin/view/coupon/coupon";
    }

    @GetMapping("/register")
    public String getCouponRegisterPage() {
        return "admin/view/coupon/coupon-register";
    }

    @GetMapping("/total-percentage-coupon-register")
    public String getTotalPercentageCouponRegisterPage() {
        return "admin/view/coupon/total-percentage-coupon-register";
    }

    @GetMapping("/flat-discount-coupon-register")
    public String getFlatDiscountCouponRegisterPage() {
        return "admin/view/coupon/flat-discount-coupon-register";
    }

    @GetMapping("/book-percentage-coupon-register")
    public String getBookPercentageCouponRegisterPage() {
        return "admin/view/coupon/book-percentage-coupon-register";
    }

    @GetMapping("/book-flat-discount-coupon-register")
    public String getBookFlatDiscountCouponRegisterPage() {
        return "admin/view/coupon/book-flat-discount-coupon-register";
    }

    @GetMapping("/category-percentage-coupon-register")
    public String getCategoryPercentageCouponRegisterPage() {
        return "admin/view/coupon/category-percentage-coupon-register";
    }

    @GetMapping("/category-flat-discount-coupon-register")
    public String getCategoryFlatDiscountCouponRegisterPage() {
        return "admin/view/coupon/category-flat-discount-coupon-register";
    }
}
