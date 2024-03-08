package store.mybooks.front.book.controller;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.admin.author.dto.response.AuthorGetResponse;
import store.mybooks.front.admin.book.model.response.BookDetailResponse;
import store.mybooks.front.admin.category.model.response.CategoryIdAndName;
import store.mybooks.front.admin.tag.model.response.TagGetResponseForBookDetail;
import store.mybooks.front.book.service.BookService;

/**
 * packageName    : store.mybooks.front.book.controller <br/>
 * fileName       : BookController<br/>
 * author         : newjaehun <br/>
 * date           : 3/1/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/1/24        newjaehun       최초 생성<br/>
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;


    /**
     * methodName : getBookDetailPage
     * author : newjaehun
     * description : 도서 상세페이지 호출.
     *
     * @param bookId Long
     * @param model Model
     * @return string
     */
    @GetMapping("/{id}")
    public String getBookDetailPage(@PathVariable("id") Long bookId, Model model) {
        BookDetailResponse book = bookService.getBook(bookId);
        model.addAttribute("book", book);
        model.addAttribute("authorNameList", book.getAuthorList().stream()
                .map(AuthorGetResponse::getName)
                .collect(Collectors.joining(", ")));
        model.addAttribute("tagNameList", book.getTagList().stream()
                .map(TagGetResponseForBookDetail::getName)
                .collect(Collectors.joining(", ")));
        model.addAttribute("categoryNameList", book.getCategoryList().stream()
                .map(CategoryIdAndName::getName)
                .collect(Collectors.joining(", ")));
        return "book-details";
    }

}
