package store.mybooks.front.admin.book.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import store.mybooks.front.admin.author.service.AuthorService;
import store.mybooks.front.admin.book.model.request.BookCreateRequest;
import store.mybooks.front.admin.book.model.request.BookModifyRequest;
import store.mybooks.front.admin.book.service.BookAdminService;
import store.mybooks.front.admin.category.service.CategoryService;
import store.mybooks.front.admin.publisher.service.PublisherService;
import store.mybooks.front.admin.tag.service.TagService;

/**
 * packageName    : store.mybooks.front.admin.book.controller <br/>
 * fileName       : BookAdminController<br/>
 * author         : newjaehun <br/>
 * date           : 2/26/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 2/26/24        newjaehun       최초 생성<br/>
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/book")
public class BookAdminController {
    private final BookAdminService bookAdminService;
    private final PublisherService publisherService;
    private final CategoryService categoryService;
    private final TagService tagService;
    private final AuthorService authorService;

    @GetMapping
    public String getBookPage(@PageableDefault(size = 8) Pageable pageable, Model model) {
        model.addAttribute("books", bookAdminService.getBooks(pageable));
        return "admin/view/book";
    }

    @GetMapping("/register")
    public String getBookRegisterPage(Model model) {
//        model.addAttribute("publishers", publisherService.getPublisherList());
        model.addAttribute("categories", categoryService.getHighestCategories());
//        model.addAttribute("tags", tagService.getTags());
//        model.addAttribute("authors", authorService.getPageAuthors());
        model.addAttribute("bookStatuses", bookAdminService.getBookStatus());
        return "admin/view/book-register";
    }

    @PostMapping("/register")
    public String createBook(@ModelAttribute BookCreateRequest bookCreateRequest) {
        bookAdminService.createBook(bookCreateRequest);
        return "redirect:/admin/book/register";
    }

    @GetMapping("/update")
    public String getBookUpdatePage(@RequestParam("id") Long bookId, Model model) {
        model.addAttribute("book", bookAdminService.getBook(bookId));
        return "admin/view/book-update";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute BookModifyRequest modifyRequest) {
        bookAdminService.updateBook(modifyRequest);
        return "redirect:/admin/book";
    }
}
