package store.mybooks.front.book.publisher.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import store.mybooks.front.book.publisher.adaptor.PublisherAdaptor;
import store.mybooks.front.book.publisher.dto.request.PublisherCreateRequest;
import store.mybooks.front.book.publisher.dto.request.PublisherDeleteRequest;
import store.mybooks.front.book.publisher.dto.request.PublisherModifyRequest;
import store.mybooks.front.book.publisher.dto.request.PublisherRequest;
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

    /**
     * methodName : getPublisherList<br>
     * author : minsu11<br>
     * description :
     * <br> *
     *
     * @return list
     */
    public List<PublisherResponse> getPublisherList() {
        return publisherAdaptor.getPublisherList().getContent();
    }

    public boolean registerPublisher(PublisherCreateRequest request) {
        try {
            publisherAdaptor.registerPublisher(request);

        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }

    public boolean updatePublisher(PublisherRequest request) {
        try {
            publisherAdaptor.updatePublisher(new PublisherModifyRequest(request.getName()), request.getId());
        } catch (RuntimeException e
        ) {
            return false;
        }
        return true;
    }

    public boolean deletePublisher(PublisherDeleteRequest request) {
        try {
            publisherAdaptor.deletePublisher(request.getId());

        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }
}
