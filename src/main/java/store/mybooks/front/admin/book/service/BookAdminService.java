package store.mybooks.front.admin.book.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.book.adaptor.BookAdminAdaptor;
import store.mybooks.front.admin.book.model.request.BookCreateRequest;
import store.mybooks.front.admin.book.model.request.BookModifyRequest;
import store.mybooks.front.admin.book.model.response.BookBriefResponse;
import store.mybooks.front.admin.book.model.response.BookDetailResponse;
import store.mybooks.front.admin.book.model.response.BookStatusGetResponse;
import store.mybooks.front.pageable.dto.response.PageResponse;

/**
 * packageName    : store.mybooks.front.admin.book.service <br/>
 * fileName       : BookAdminService<br/>
 * author         : newjaehun <br/>
 * date           : 2/26/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 2/26/24        newjaehun       최초 생성<br/>
 */
@Service
@RequiredArgsConstructor
public class BookAdminService {
    private final BookAdminAdaptor bookAdminAdaptor;

    public PageResponse<BookBriefResponse> getBooks(Pageable pageable) {
        return bookAdminAdaptor.getPagedBriefBooks(pageable);
    }

    public BookDetailResponse getBook(Long bookId) {
        return bookAdminAdaptor.getDetailBook(bookId);
    }

    public void createBook(BookCreateRequest bookCreateRequest) {
        bookAdminAdaptor.createBook(bookCreateRequest);
    }


    public void updateBook(BookModifyRequest modifyRequest) {
        bookAdminAdaptor.updateBook(modifyRequest.getBookId(), modifyRequest);
    }

    public List<BookStatusGetResponse> getBookStatus() {
        return bookAdminAdaptor.getBookStatus();
    }
}
