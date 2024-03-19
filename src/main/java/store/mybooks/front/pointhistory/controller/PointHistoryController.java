package store.mybooks.front.pointhistory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.pageable.dto.response.PageResponse;
import store.mybooks.front.pointhistory.model.PointHistoryGetResponse;
import store.mybooks.front.pointhistory.service.PointHistoryService;

/**
 * packageName    : store.mybooks.front.pointhistory.controller
 * fileName       : PointHistoryController
 * author         : damho-lee
 * date           : 3/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/19/24          damho-lee          최초 생성
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/user/point-history")
public class PointHistoryController {
    private final PointHistoryService pointHistoryService;

    @GetMapping
    public String getPointHistoryPage(@PageableDefault Pageable pageable, Model model) {
        PageResponse<PointHistoryGetResponse> page = pointHistoryService.getPointHistories(pageable);
        model.addAttribute("points", page);
        return "point-history";
    }
}
