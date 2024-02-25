package store.mybooks.front.book.publisher.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import store.mybooks.front.book.publisher.adaptor.PublisherAdaptor;
import store.mybooks.front.book.publisher.dto.response.PublisherResponse;

/**
 * packageName    : store.mybooks.front.book.publisher.service<br>
 * fileName       : PublisherService<br>
 * author         : minsu11<br>
 * date           : 2/25/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/25/24        minsu11       최초 생성<br>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherAdaptor publisherAdaptor;

    public List<PublisherResponse> getPublisherList() {
        List<PublisherResponse> list = publisherAdaptor.getPublisherList().getContent();
        log.info("value: {}", list);
        return list;
    }

}
