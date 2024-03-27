package store.mybooks.front.elastic.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import store.mybooks.front.admin.book.model.response.BookBriefResponse;
import store.mybooks.front.elastic.service.ElasticService;
import store.mybooks.front.pageable.dto.response.PageResponse;

/**
 * packageName    : store.mybooks.front.elastic.controller <br/>
 * fileName       : ElasticController<br/>
 * author         : newjaehun <br/>
 * date           : 3/20/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/20/24        newjaehun       최초 생성<br/>
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class ElasticController {
    private final ElasticService elasticService;

    @GetMapping
    public String getSearchResultPage(@RequestParam("query") String query,
                                      @RequestParam(value = "order", required = false) String order,
                                      @PageableDefault(size = 8, sort = "book_view_count", direction = Sort.Direction.DESC)
                                      Pageable pageable, Model model) {
        PageResponse<BookBriefResponse> result = elasticService.getSearchResultPage(query, pageable, order);

        model.addAttribute("order", (order != null) ? order : "accuracy");
        if ("rate".equals(order)) {
            List<BookBriefResponse> checkReviewCount = result.getContent().stream()
                    .filter(book -> book.getReviewCount() != null && book.getReviewCount() >= 100)
                    .collect(Collectors.toList());
            result.setContent(checkReviewCount);
        }
        model.addAttribute("books", result);
        return "search-display";
    }
}
