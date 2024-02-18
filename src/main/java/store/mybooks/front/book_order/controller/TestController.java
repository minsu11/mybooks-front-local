package store.mybooks.front.book_order.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import store.mybooks.front.book_order.model.Response;
import store.mybooks.front.book_order.service.TestService;

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
public class TestController {
    private final TestService testService;

    @GetMapping("/api/orders-statuses")
    public ResponseEntity<String> test() {
        List<Response> data = testService.getTestResult();
        return new ResponseEntity<>(data.get(0).toString(), HttpStatus.OK);
    }

}
