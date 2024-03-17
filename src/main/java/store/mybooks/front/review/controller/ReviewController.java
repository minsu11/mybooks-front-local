package store.mybooks.front.review.controller;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import store.mybooks.front.review.controller.dto.request.ReviewCreateRequest;
import store.mybooks.front.review.controller.dto.request.ReviewModifyRequest;
import store.mybooks.front.review.controller.service.ReviewService;

/**
 * packageName    : store.mybooks.front.review.controller<br>
 * fileName       : ReviewController<br>
 * author         : masiljangajji<br>
 * date           : 3/17/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/17/24        masiljangajji       최초 생성
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/register")
    public String reviewRegisterForm() {
        return "review-register";
    }

    @PostMapping("/register")
    public String reviewRegister(@ModelAttribute ReviewCreateRequest createRequest,
                                 @RequestParam(value = "contentImage", required = false) MultipartFile contentImages)
            throws IOException {

        // todo 이거 진짜로 바꾸기
        createRequest.setOrderDetailId(1L);
        reviewService.createReview(createRequest, contentImages);
        return "redirect:/review";
    }

    @GetMapping
    public String getReviewByUserId(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("reviews", reviewService.getAllUserReview(pageable));
        return "review";
    }

    @GetMapping("/modify")
    public String modifyForm(@RequestParam(name = "reviewId") Long reviewId, Model model) {
        model.addAttribute("review", reviewService.getUserReview(reviewId));
        return "review-modify";
    }

    @PostMapping("/modify")
    public String modifyReview(@RequestParam(name = "reviewId") Long reviewId,
                               @ModelAttribute ReviewModifyRequest request) {
        reviewService.modifyReview(reviewId, request);
        return "redirect:/review";
    }

}
