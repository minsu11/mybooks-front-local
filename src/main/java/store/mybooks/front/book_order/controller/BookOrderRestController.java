package store.mybooks.front.book_order.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import store.mybooks.front.book_order.model.Response;
import store.mybooks.front.book_order.service.BookOrderService;

/**
 * packageName    : store.mybooks.front.book_order.controller
 * fileName       : TestController
 * author         : minsu11
 * date           : 2/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/19/24        minsu11       최초 생성
 */
@RestController
@RequiredArgsConstructor
public class BookOrderRestController {
    private final BookOrderService testService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        List<Response> data = testService.getTestResult();
        String result = "result: " + data.size();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
