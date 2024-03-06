package store.mybooks.front.user_coupon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.user_coupon.service.UserCouponService;

/**
 * packageName    : store.mybooks.front.user_coupon.controller
 * fileName       : UserCouponController
 * author         : damho-lee
 * date           : 3/5/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/5/24          damho-lee          최초 생성
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/user-coupon")
public class UserCouponController {
    private final UserCouponService userCouponService;

    @RequestMapping
    public String getUserCouponPage(Model model, @PageableDefault Pageable pageable) {
        model.addAttribute("userCoupons", userCouponService.getUserCoupons(pageable));
        return "user-coupon-page";
    }
}
