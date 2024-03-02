package store.mybooks.front.book.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.admin.author.service.AuthorService;
import store.mybooks.front.admin.category.service.CategoryService;
import store.mybooks.front.admin.publisher.service.PublisherService;
import store.mybooks.front.admin.tag.service.TagService;
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
    private final PublisherService publisherService;
    private final CategoryService categoryService;
    private final TagService tagService;
    private final AuthorService authorService;

    @GetMapping("/{id}")
    public String getBookDetailPage(@PathVariable("id") Long bookId, Model model) {
        model.addAttribute("book", bookService.getBook(bookId));
//        model.addAttribute("categoryList", )

        return "book-details";
    }

}
