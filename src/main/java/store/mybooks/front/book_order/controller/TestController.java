package store.mybooks.front.book_order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class TestController {

    @GetMapping("/api/orders-statuses")
    public ResponseEntity<String> test() {

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

}
