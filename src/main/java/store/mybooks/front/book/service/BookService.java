package store.mybooks.front.book.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.book.model.response.BookDetailResponse;
import store.mybooks.front.admin.book.model.response.BookLikeResponse;
import store.mybooks.front.admin.book.model.response.BookPopularityResponse;
import store.mybooks.front.admin.book.model.response.BookPublicationDateResponse;
import store.mybooks.front.admin.book.model.response.BookRatingResponse;
import store.mybooks.front.admin.book.model.response.BookReviewResponse;
import store.mybooks.front.book.adaptor.BookAdaptor;

/**
 * packageName    : store.mybooks.front.book.service <br/>
 * fileName       : BookService<br/>
 * author         : newjaehun <br/>
 * date           : 3/1/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/1/24        newjaehun       최초 생성<br/>
 */
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookAdaptor bookAdaptor;

    /**
     * methodName : getBook
     * author : newjaehun
     * description : 도서 상세정보 가져오는 서비스.
     *
     * @param bookId Long
     * @return bookDetailResponse
     */
    public BookDetailResponse getBook(Long bookId) {
        return bookAdaptor.getBook(bookId);
    }

    public List<BookPopularityResponse> getBookPopularitySortList() {
        return bookAdaptor.getBookPopularitySortList();
    }

    public List<BookLikeResponse> getBookLikeSortList() {
        return bookAdaptor.getBookLikeSortList();
    }

    public List<BookReviewResponse> getBookReviewSortList() {
        return bookAdaptor.getBookReviewSortList();
    }

    public List<BookRatingResponse> getBookRatingSortList() {
        return bookAdaptor.getBookRatingSortList();
    }

    public List<BookPublicationDateResponse> getBookPublicationDateSortList() {
        return bookAdaptor.getBookPublicationDateSortList();
    }
}
