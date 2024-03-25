package store.mybooks.front.pointhistory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.pointhistory.model.PointResponseForUser;
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

    /**
     * methodName : getPointHistoryPage <br>
     * author : damho-lee <br>
     * description : 포인트 내역 페이지 조회.<br>
     *
     * @param pageable Pageable
     * @param model Model
     * @return string
     */
    @GetMapping
    public String getPointHistoryPage(@PageableDefault Pageable pageable, Model model) {
        PointResponseForUser pointResponseForUser = pointHistoryService.getPointHistories(pageable);
        model.addAttribute("remainPoint", pointResponseForUser.getRemainPoint());
        model.addAttribute("points", pointResponseForUser.getPointHistoryResponsePage());
        return "point-history";
    }
}
