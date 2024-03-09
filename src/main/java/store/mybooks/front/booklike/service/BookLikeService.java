package store.mybooks.front.booklike.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.book.model.response.BookBriefResponse;
import store.mybooks.front.booklike.adaptor.BookLikeAdaptor;
import store.mybooks.front.pageable.dto.response.PageResponse;
import store.mybooks.front.pageable.dto.response.Pageable;

/**
 * packageName    : store.mybooks.front.booklike.service <br/>
 * fileName       : BookLikeService<br/>
 * author         : newjaehun <br/>
 * date           : 3/9/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/9/24        newjaehun       최초 생성<br/>
 */
@Service
@RequiredArgsConstructor
public class BookLikeService {
    private final BookLikeAdaptor bookLikeAdaptor;

    /**
     * methodName : getPageUserBookLike
     * author : newjaehun
     * description : 회원이 좋아요한 페이징된 도서목록 반환.
     *
     * @param pageable Pageable
     * @return pageResponse
     */
    public PageResponse<BookBriefResponse> getPageUserBookLike(Pageable pageable) {
        return bookLikeAdaptor.getPageUserBookLike(pageable);
    }

    /**
     * methodName : updateBookLike
     * author : newjaehun
     * description : 사용자가 도서 좋아요 하는 기능.
     *
     * @param bookId Long
     * @return boolean
     */
    public boolean updateBookLike(Long bookId) {
        return bookLikeAdaptor.updateBookLike(bookId);
    }
}
