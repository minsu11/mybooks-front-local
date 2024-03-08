package store.mybooks.front.admin.publisher.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.admin.publisher.dto.request.PublisherCreateRequest;
import store.mybooks.front.admin.publisher.dto.request.PublisherDeleteRequest;
import store.mybooks.front.admin.publisher.dto.request.PublisherModifyRequest;
import store.mybooks.front.admin.publisher.dto.request.PublisherRequest;
import store.mybooks.front.admin.publisher.dto.response.PublisherResponse;
import store.mybooks.front.admin.publisher.service.PublisherService;
import store.mybooks.front.pageable.dto.response.PageResponse;

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
    public String viewPublisher(@PageableDefault Pageable pageable, ModelMap modelMap) {
        PageResponse<PublisherResponse> publisherResponseList = publisherService.getPagedPublisher(pageable);
        modelMap.put("publisherList", publisherResponseList);
        return "admin/view/publisher/publisher-view";
    }


    /**
     * methodName : viewRegisterPublisher<br>
     * author : minsu11<br>
     * description : 출판사를 등록하는 {@code view}의 경로를 반환
     * <br> *
     *
     * @param modelMap {@code view}에 보낼 정보를 담고 있음
     * @return 출판사 등록하는 {@code view}의 경로
     */
    @GetMapping("/register")
    public String viewRegisterPublisher(ModelMap modelMap) {
        modelMap.put("pathValue", "register");

        return "admin/view/publisher/publisher-register-view";
    }

    /**
     * methodName : doRegisterPublisher<br>
     * author : minsu11<br>
     * description : 출판사의 정보를 저장 한 뒤, 기존에 있던 저자의 {@code list}를
     * 보여주는 {@code view}를 보여줌. 저장에 실패 시 등록하는 {@code view}를 다시 보여줌
     * <br> *
     *
     * @param request 저장할 출판사의 정보
     * @return {@code view}의 경로
     */
    @PostMapping("/register")
    public String doRegisterPublisher(@ModelAttribute PublisherCreateRequest request) {
        if (publisherService.registerPublisher(request)) {
            return "redirect:/admin/publishers";
        }
        return "redirect:/admin/publishers";
    }

    /**
     * methodName : viewRegisterPublisher<br>
     * author : minsu11<br>
     * description : 출판사의 정보를 수정할 {@code view}를 보여줌.
     * <br> *
     *
     * @param id       수정할 출판사 아이디
     * @param request  수정할 출판사 정보
     * @param modelMap {@code view}로 전달
     * @return string
     */
    @GetMapping("/{id}")
    public String viewUpdatePublisher(
            @PathVariable Integer id,
            @ModelAttribute PublisherModifyRequest request,
            ModelMap modelMap) {
        modelMap.put("id", id);
        modelMap.put("modifyPublisher", request);
        modelMap.put("pathValue", "update");
        return "admin/view/publisher/publisher-register-view";
    }

    /**
     * methodName : doUpdatePublisher<br>
     * author : minsu11<br>
     * description : 출판사의 정보를 수정. 수정이 완료되면 기존의 출판사 목록을 보여주는 {@code view}를 보여주고,
     * 실패 시 수정하는 {@code view}를 반환<br>
     * <br> *
     *
     * @param request  수정할 저자의 정보<br>
     * @param modelMap {@code view}에 보여줄 정보를 전달<br>
     * @return 수정 성공 시 기존의 출판사의 목록을 보여주는 {@code view}경로 반환하고, 실패 시 수정하는 {@code view}를 반환
     */
    @PostMapping("/update")
    public String doUpdatePublisher(
            @ModelAttribute PublisherRequest request,
            ModelMap modelMap) {
        log.info("id value:{}", request.getId());
        if (publisherService.updatePublisher(request)) {
            return "redirect:/admin/publishers";
        }
        modelMap.put("id", request.getId());
        modelMap.put("modifyPublisher", request.getName());
        return "redirect:/admin/publishers/" + request.getId();

    }

    /**
     * methodName : doDelete<br>
     * author : minsu11<br>
     * description : 출판사의 정보를 삭제.
     * <br> *
     *
     * @param request 삭제할 출판사의 정보가 담김
     * @return 기존의 출판사의 목록을 보여주는 {@code view}경로 반환
     */
    @PostMapping("/delete")
    public String doDelete(@ModelAttribute PublisherDeleteRequest request) {
        log.info("delete value: {}", request.getId());
        publisherService.deletePublisher(request);
        return "redirect:/admin/publishers";
    }
}
