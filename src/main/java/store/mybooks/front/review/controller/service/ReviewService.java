package store.mybooks.front.review.controller.service;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import store.mybooks.front.pageable.dto.response.PageResponse;
import store.mybooks.front.review.controller.adaptor.ReviewAdaptor;
import store.mybooks.front.review.controller.dto.request.ReviewCreateRequest;
import store.mybooks.front.review.controller.dto.request.ReviewModifyRequest;
import store.mybooks.front.review.controller.dto.response.ReviewDetailGetResponse;
import store.mybooks.front.review.controller.dto.response.ReviewGetResponse;

/**
 * packageName    : store.mybooks.front.review.controller.service<br>
 * fileName       : ReviewService<br>
 * author         : masiljangajji<br>
 * date           : 3/17/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/17/24        masiljangajji       최초 생성
 */

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewAdaptor reviewAdaptor;

    public void createReview(ReviewCreateRequest request, MultipartFile file) throws IOException {
        reviewAdaptor.createReview(request,file);
    }

    public void modifyReview(Long reviewId,ReviewModifyRequest request){
        reviewAdaptor.modifyUserReview(reviewId,request);
    }
    public PageResponse<ReviewGetResponse> getAllUserReview(Pageable pageable){
        return reviewAdaptor.getAllUserReview(pageable);
    }

    public ReviewGetResponse getUserReview(Long reviewId){
        return reviewAdaptor.getUserReview(reviewId);
    }

    public PageResponse<ReviewDetailGetResponse> getBookReview(Pageable pageable, Long bookId){
        return reviewAdaptor.getBookReview(pageable,bookId);
    }

}
