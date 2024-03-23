package store.mybooks.front.home;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.admin.book.model.response.BookLikeResponse;
import store.mybooks.front.admin.book.model.response.BookPopularityResponse;
import store.mybooks.front.admin.book.model.response.BookPublicationDateResponse;
import store.mybooks.front.admin.book.model.response.BookRatingResponse;
import store.mybooks.front.admin.book.model.response.BookReviewResponse;
import store.mybooks.front.admin.book.service.BookAdminService;
import store.mybooks.front.book.service.BookService;

/**
 * packageName    : store.mybooks.front
 * fileName       : HomeController
 * author         : masiljangajji
 * date           : 2/26/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/26/24        masiljangajji       최초 생성
 */

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BookAdminService bookAdminService;
    private final BookService bookService;

    @RequestMapping("/")
    public String home(@PageableDefault(size = 8) Pageable pageable, Model model) {

        List<BookPopularityResponse> bookPopularitySortList = bookService.getBookPopularitySortList();
        List<BookLikeResponse> bookLikeSortList = bookService.getBookLikeSortList();
        List<BookReviewResponse> bookReviewSortList = bookService.getBookReviewSortList();
        List<BookRatingResponse> bookRatingSortList = bookService.getBookRatingSortList();
        List<BookPublicationDateResponse> bookPublicationDateSortList = bookService.getBookPublicationDateSortList();

        model.addAttribute("popularList", bookPopularitySortList);
        model.addAttribute("likeList", bookLikeSortList);
        model.addAttribute("reviewList", bookReviewSortList);
        model.addAttribute("ratingList", bookRatingSortList);
        model.addAttribute("publicationList", bookPublicationDateSortList);
//        model.addAttribute("books", bookAdminService.getBooks(pageable));
        return "index";
    }

}