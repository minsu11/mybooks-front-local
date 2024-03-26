package store.mybooks.front.review.controller;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import store.mybooks.front.review.dto.request.ReviewCreateRequest;
import store.mybooks.front.review.dto.request.ReviewModifyRequest;
import store.mybooks.front.review.service.ReviewService;

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

    /**
     * methodName : reviewRegisterForm
     * author : masiljangajji
     * description : 리뷰 작성을 위한 페이지로 이동
     *
     * @param orderId       주문 아이디
     * @param orderDetailId 주문 상세 아이디
     * @param model         model
     * @return string
     */
    @GetMapping("{orderId}/register/{orderDetailId}")
    public String reviewRegisterForm(@PathVariable(name = "orderId") Long orderId,
                                     @PathVariable(name = "orderDetailId") Long orderDetailId, Model model) {
        model.addAttribute("orderId", orderId);
        model.addAttribute("orderDetailId", orderDetailId);

        return "review-register";
    }

    /**
     * methodName : reviewRegister
     * author : masiljangajji
     * description : 리뷰 등록을 처리
     *
     * @param createRequest 주문 정보 및 리뷰 제목 본문 별점 정보
     * @param contentImages 이미지파일
     * @return string
     * @throws IOException the io exception
     */
    @PostMapping("/register")
    public String reviewRegister(@ModelAttribute ReviewCreateRequest createRequest,
                                 @RequestParam(value = "contentImage", required = false) MultipartFile contentImages)
            throws IOException {

        reviewService.createReview(createRequest, contentImages);
        return "redirect:/review";
    }

    /**
     * methodName : getReviewByUserId
     * author : masiljangajji
     * description : 유저의 모든 리뷰를 가져옴
     *
     * @param pageable pageable
     * @param model    model
     * @return string
     */
    @GetMapping
    public String getReviewByUserId(@PageableDefault(size = 8) Pageable pageable, Model model) {
        model.addAttribute("reviews", reviewService.getAllUserReview(pageable));
        return "review";
    }

    /**
     * methodName : getReviewByBookId
     * author : masiljangajji
     * description : 책의 모든 리뷰를 가져옴
     *
     * @param bookId   책 아이디
     * @param pageable pageable
     * @param model    model
     * @return string
     */
    @GetMapping("/book/{bookId}")
    public String getReviewByBookId(@PathVariable(name = "bookId") Long bookId,
                                    @PageableDefault(size = 5) Pageable pageable, Model model) {
        model.addAttribute("reviews", reviewService.getBookReview(pageable, bookId));
        return "review-book";
    }

    /**
     * methodName : modifyForm
     * author : masiljangajji
     * description : 리뷰 수정페이지로 이동
     *
     * @param reviewId 리뷰 아이디
     * @param model    model
     * @return string
     */
    @GetMapping("/modify")
    public String modifyForm(@RequestParam(name = "reviewId") Long reviewId, Model model) {
        model.addAttribute("review", reviewService.getUserReview(reviewId));
        return "review-modify";
    }

    /**
     * methodName : modifyReview
     * author : masiljangajji
     * description : 리뷰를 수정함
     *
     * @param reviewId      리뷰 아이디
     * @param request       타이틀 본문 별점
     * @param contentImages 이미지
     * @return string
     * @throws IOException the io exception
     */
    @PostMapping("/modify")
    public String modifyReview(@RequestParam(name = "reviewId") Long reviewId,
                               @ModelAttribute ReviewModifyRequest request,
                               @RequestParam(value = "contentImage", required = false) MultipartFile contentImages)
            throws IOException {
        reviewService.modifyReview(reviewId, request, contentImages);
        return "redirect:/review";
    }


}
