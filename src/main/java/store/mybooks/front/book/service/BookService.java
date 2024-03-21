package store.mybooks.front.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.book.model.response.BookDetailResponse;
import store.mybooks.front.admin.book.model.response.BookGetResponseForOrder;
import store.mybooks.front.admin.book.model.response.BookStockResponse;
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

    /**
     * 바로 구매 정보를 조회.
     *
     * @param bookId the book id
     * @return the book for order
     */
    public BookGetResponseForOrder getBookForOrder(Long bookId) {
        return bookAdaptor.getBookForOrder(bookId);
    }

    public BookStockResponse getBookStockResponse(Long bookId) {
        return bookAdaptor.getBookStockResponse(bookId);

    }
}
