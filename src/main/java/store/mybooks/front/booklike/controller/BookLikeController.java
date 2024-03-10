package store.mybooks.front.booklike.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.admin.book.model.response.BookBriefResponse;
import store.mybooks.front.booklike.service.BookLikeService;
import store.mybooks.front.pageable.dto.response.PageResponse;

/**
 * packageName    : store.mybooks.front.booklike.controller <br/>
 * fileName       : BookLikeController<br/>
 * author         : newjaehun <br/>
 * date           : 3/9/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/9/24        newjaehun       최초 생성<br/>
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/book-like")
public class BookLikeController {
    private final BookLikeService bookLikeService;

    /**
     * methodName : viewUserBookLike
     * author : newjaehun
     * description : 회원이 좋아요한 페이징된 도서목록 반환.
     *
     * @param pageable Pageable
     * @param model Model
     * @return string
     */
    @GetMapping
    public String viewUserBookLike(@PageableDefault(size = 8) Pageable pageable, Model model) {
        PageResponse<BookBriefResponse> userBookLikeList = bookLikeService.getPageUserBookLike(pageable);
        model.addAttribute("bookLikeList", userBookLikeList);
        return "user-book-like";
    }

    /**
     * methodName : updateBookLike
     * author : newjaehun
     * description : 사용자가 도서 좋아요 하는 기능.
     *
     * @param bookId long
     * @return string
     */
    @PostMapping("/{bookId}")
    public String updateBookLike(@PathVariable("bookId") Long bookId) {
        if (bookLikeService.updateBookLike(bookId)) {
            return "redirect:/book/" + bookId + "?like=true";
        }
        return "redirect:/book/bookId";
    }
}
