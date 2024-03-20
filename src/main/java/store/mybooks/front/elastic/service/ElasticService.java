package store.mybooks.front.elastic.service;

import static io.lettuce.core.GeoArgs.Sort.desc;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.book.model.response.BookBriefResponse;
import store.mybooks.front.elastic.adaptor.ElasticAdaptor;
import store.mybooks.front.pageable.dto.response.PageResponse;
import store.mybooks.front.pageable.dto.response.Pageable;

/**
 * packageName    : store.mybooks.front.elastic.service <br/>
 * fileName       : ElasticService<br/>
 * author         : newjaehun <br/>
 * date           : 3/20/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/20/24        newjaehun       최초 생성<br/>
 */
@Service
@RequiredArgsConstructor
public class ElasticService {
    private final ElasticAdaptor elasticAdaptor;

//    public PageResponse<BookBriefResponse> getSearchResultPage(String query, String type) {
//        return elasticAdaptor.searchPaged(query, pageable);
//    }
}
