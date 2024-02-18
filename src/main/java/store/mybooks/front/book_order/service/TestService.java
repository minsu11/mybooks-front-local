package store.mybooks.front.book_order.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.mybooks.front.book_order.adaptor.TestAdaptor;
import store.mybooks.front.book_order.model.Response;

/**
 * packageName    : store.mybooks.front.book_order.service
 * fileName       : TestService
 * author         : minsu11
 * date           : 2/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/19/24        minsu11       최초 생성
 */
@Service
@RequiredArgsConstructor
public class TestService {
    private final TestAdaptor testAdaptor;

    public List<Response> getTestResult() {
        List<Response> responseList = testAdaptor.test();
        return responseList;
    }

}
