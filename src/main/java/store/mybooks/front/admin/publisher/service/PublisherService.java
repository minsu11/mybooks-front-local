package store.mybooks.front.admin.publisher.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.publisher.adaptor.PublisherAdaptor;
import store.mybooks.front.admin.publisher.dto.request.PublisherCreateRequest;
import store.mybooks.front.admin.publisher.dto.request.PublisherDeleteRequest;
import store.mybooks.front.admin.publisher.dto.request.PublisherModifyRequest;
import store.mybooks.front.admin.publisher.dto.request.PublisherRequest;
import store.mybooks.front.admin.publisher.dto.response.PublisherResponse;
import store.mybooks.front.pageable.dto.response.PageResponse;

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
     * methodName : getAllPublishers<br>
     * author : newjaehun<br>
     * description : 전체 출판사 목록을 조회
     * <br> *
     * @return list
     */
    public List<PublisherResponse> getAllPublishers() {
        return publisherAdaptor.getAllPublishers();
    }


    /**
     * methodName : getPublisherList<br>
     * author : minsu11<br>
     * description : 페이징된 출판사의 목록을 조회
     * <br> *
     *
     * @return 출판사 목록 조회
     */
    public PageResponse<PublisherResponse> getPagedPublisher(Pageable pageable) {
        return publisherAdaptor.getPagedPublishers(pageable);
    }

    public boolean registerPublisher(PublisherCreateRequest request) {
        try {
            publisherAdaptor.registerPublisher(request);

        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }

    /**
     * methodName : updatePublisher<br>
     * author : minsu11<br>
     * description : 출판사 정보를 수정
     * <br> *
     *
     * @param request 수정할 출판사 정보<br>
     * @return 수정 성공 시 {@code true} 실패 시 {@code false}
     */
    public boolean updatePublisher(PublisherRequest request) {
        try {
            publisherAdaptor.updatePublisher(new PublisherModifyRequest(request.getName()), request.getId());
        } catch (RuntimeException e
        ) {
            return false;
        }
        return true;
    }

    /**
     * methodName : deletePublisher<br>
     * author : minsu11<br>
     * description : 출판사 정보를 삭제. 삭제 성공 시 {@code true} 반환하고, 실패 시 {@code false} 반환
     * <br> *
     *
     * @param request 삭제할 출판사 정보<br>
     * @return 삭제 성공 시 {@code true} 반환하고, 실패 시 {@code false} 반환
     */
    public boolean deletePublisher(PublisherDeleteRequest request) {
        try {
            publisherAdaptor.deletePublisher(request.getId());

        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }
}
