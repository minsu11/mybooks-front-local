package store.mybooks.front.book.publisher.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.book.publisher.dto.response.PublisherResponse;
import store.mybooks.front.book.publisher.service.PublisherService;

/**
 * packageName    : store.mybooks.front.book.publisher.controller<br>
 * fileName       : PublisherController<br>
 * author         : minsu11<br>
 * date           : 2/25/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/25/24        minsu11       최초 생성<br>
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/publishers")
public class PublisherController {
    private final PublisherService publisherService;

    @GetMapping
    public String viewPublisher(ModelMap modelMap) {
        log.info("viewPublisher 시작");
        List<PublisherResponse> publisherResponseList = publisherService.getPublisherList();
        modelMap.put("publisherList", publisherResponseList);
        return "admin/view/publisher-view";
    }
}
