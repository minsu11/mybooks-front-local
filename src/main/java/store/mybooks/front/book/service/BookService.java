package store.mybooks.front.book.service;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
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
    private final RedisTemplate<String, Object> redisTemplate;

    private static final String BOOK_POPULARITY_KEY = "main:popularity";
    private static final String BOOK_LIKE_KEY = "main:like";
    private static final String BOOK_REVIEW_KEY = "main:review";
    private static final String BOOK_RATING_KEY = "main:rating";
    private static final String BOOK_PUBLICATION_KEY = "main:publication";


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

    @Scheduled(cron = "0 5 0 * * *")
    private void fetchAndCacheBookPopularitySortList() {
        redisTemplate.delete(BOOK_POPULARITY_KEY);

        List<BookPopularityResponse> bookPopularitySortList = bookAdaptor.getBookPopularitySortList();
        redisTemplate.opsForValue().set(BOOK_POPULARITY_KEY, bookPopularitySortList);
    }

    public List<BookPopularityResponse> getBookPopularitySortList() {
        List<BookPopularityResponse> popularityList =
                (List<BookPopularityResponse>) redisTemplate.opsForValue().get(BOOK_POPULARITY_KEY);

        if (Objects.isNull(popularityList)) {
            fetchAndCacheBookPopularitySortList();
            popularityList = (List<BookPopularityResponse>) redisTemplate.opsForValue().get(BOOK_POPULARITY_KEY);
        }

        return popularityList;
    }

    @Scheduled(cron = "0 5 0 * * *")
    private void fetchAndCacheBookLikeSortList() {
        redisTemplate.delete(BOOK_LIKE_KEY);

        List<BookLikeResponse> bookLikeSortList = bookAdaptor.getBookLikeSortList();
        redisTemplate.opsForValue().set(BOOK_LIKE_KEY, bookLikeSortList);
    }

    public List<BookLikeResponse> getBookLikeSortList() {
        List<BookLikeResponse> bookLikeSortList =
                (List<BookLikeResponse>) redisTemplate.opsForValue().get(BOOK_LIKE_KEY);

        if (Objects.isNull(bookLikeSortList)) {
            fetchAndCacheBookLikeSortList();
            bookLikeSortList = (List<BookLikeResponse>) redisTemplate.opsForValue().get(BOOK_LIKE_KEY);
        }

        return bookLikeSortList;
    }

    @Scheduled(cron = "0 5 0 * * *")
    private void fetchAndCacheBookReviewSortList() {
        redisTemplate.delete(BOOK_REVIEW_KEY);

        List<BookReviewResponse> bookReviewSortList = bookAdaptor.getBookReviewSortList();
        redisTemplate.opsForValue().set(BOOK_REVIEW_KEY, bookReviewSortList);
    }

    public List<BookReviewResponse> getBookReviewSortList() {
        List<BookReviewResponse> bookReviewSortList =
                (List<BookReviewResponse>) redisTemplate.opsForValue().get(BOOK_REVIEW_KEY);

        if (Objects.isNull(bookReviewSortList)) {
            bookReviewSortList = bookAdaptor.getBookReviewSortList();
            redisTemplate.opsForValue().set(BOOK_REVIEW_KEY, bookReviewSortList);
        }

        return bookReviewSortList;
    }

    @Scheduled(cron = "0 5 0 * * *")
    private void fetchAndCacheBookRatingSortList() {
        redisTemplate.delete(BOOK_RATING_KEY);

        List<BookRatingResponse> bookRatingSortList = bookAdaptor.getBookRatingSortList();
        redisTemplate.opsForValue().set(BOOK_RATING_KEY, bookRatingSortList);
    }

    public List<BookRatingResponse> getBookRatingSortList() {
        List<BookRatingResponse> bookRatingSortList =
                (List<BookRatingResponse>) redisTemplate.opsForValue().get(BOOK_RATING_KEY);

        if (Objects.isNull(bookRatingSortList)) {
            bookRatingSortList = bookAdaptor.getBookRatingSortList();
            redisTemplate.opsForValue().set(BOOK_RATING_KEY, bookRatingSortList);
        }

        return bookRatingSortList;
    }

    @Scheduled(cron = "0 5 0 * * *")
    private void fetchAndCacheBookPublicationDateSortList() {
        redisTemplate.delete(BOOK_PUBLICATION_KEY);

        List<BookPublicationDateResponse> bookPublicationDateSortList = bookAdaptor.getBookPublicationDateSortList();
        redisTemplate.opsForValue().set(BOOK_PUBLICATION_KEY, bookPublicationDateSortList);
    }

    public List<BookPublicationDateResponse> getBookPublicationDateSortList() {
        List<BookPublicationDateResponse> bookPublicationDateSortList =
                (List<BookPublicationDateResponse>) redisTemplate.opsForValue().get(BOOK_PUBLICATION_KEY);

        if (Objects.isNull(bookPublicationDateSortList)) {
            bookPublicationDateSortList = bookAdaptor.getBookPublicationDateSortList();
            redisTemplate.opsForValue().set(BOOK_PUBLICATION_KEY, bookPublicationDateSortList);
        }

        return bookPublicationDateSortList;
    }


}