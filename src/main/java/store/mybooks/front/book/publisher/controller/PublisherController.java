package store.mybooks.front.book.publisher.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import store.mybooks.front.book.publisher.dto.request.PublisherCreateRequest;
import store.mybooks.front.book.publisher.dto.request.PublisherDeleteRequest;
import store.mybooks.front.book.publisher.dto.request.PublisherModifyRequest;
import store.mybooks.front.book.publisher.dto.request.PublisherRequest;
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

    /**
     * methodName : viewPublisher<br>
     * author : minsu11<br>
     * description : 출판사의 정보를 {@code view}에 보여줌
     * <br> *
     *
     * @param modelMap {@code view}에 보여줄 출판사의 정보를 담는 {@code model}
     * @return string
     */
    @GetMapping
    public String viewPublisher(ModelMap modelMap) {
        log.info("viewPublisher 시작");
        List<PublisherResponse> publisherResponseList = publisherService.getPublisherList();
        modelMap.put("publisherList", publisherResponseList);
        return "admin/view/publisher-view";
    }


    @GetMapping("/register")
    public String viewRegisterPublisher(ModelMap modelMap) {
        modelMap.put("pathValue", "register");

        return "admin/view/publisher-register-view";
    }

    @PostMapping("/register")
    public String doRegisterPublisher(@ModelAttribute PublisherCreateRequest request) {
        if (publisherService.registerPublisher(request)) {
            return "redirect:/admin/publishers";
        }
        return "redirect:/admin/publishers";
    }

    @GetMapping("/update")
    public String viewRegisterPublisher(@RequestParam(name = "id") Integer id,
                                        @RequestParam(name = "name") PublisherModifyRequest modifyRequest,
                                        ModelMap modelMap) {
        modelMap.put("id", id);
        modelMap.put("modifyPublisher", modifyRequest);
        modelMap.put("pathValue", "update");
        log.info("수정할 출판사 아이디: {}", id);
        log.info("수정할 출판사 이름: {}", modifyRequest.getChangeName());
        return "admin/view/publisher-register-view";
    }

    @PostMapping("/update")
    public String doUpdatePublisher(
            @ModelAttribute PublisherRequest request,
            ModelMap modelMap) {
        log.info("id value:{}", request.getId());
        String beforeValue = request.getName();
        if (publisherService.updatePublisher(request)) {
            return "redirect:/admin/publishers";
        }
        modelMap.put("id", request.getId());
        modelMap.put("modifyPublisher", request.getName());
        return "redirect:/admin/publishers/update?id=" + request.getId() + "&name=" + beforeValue;

    }

    @PostMapping("/delete")
    public String doDelete(@ModelAttribute PublisherDeleteRequest request) {
        log.info("delete value: {}", request.getId());
        publisherService.deletePublisher(request);
        return "redirect:/admin/publishers";
    }
}
