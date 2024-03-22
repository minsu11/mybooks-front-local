package store.mybooks.front.elastic.service;

import static io.lettuce.core.GeoArgs.Sort.desc;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.book.model.response.BookBriefResponse;
import store.mybooks.front.elastic.adaptor.ElasticAdaptor;
import store.mybooks.front.pageable.dto.response.PageResponse;

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

    public PageResponse<BookBriefResponse> getSearchResultPage(String query, Pageable pageable, String order) {
        if (order != null) {
            Sort sort;
            switch (order) {
                case "recent":
                    sort = Sort.by(Sort.Direction.DESC, "book_publish_date");
                    break;
                case "low-price":
                    sort = Sort.by(Sort.Direction.ASC, "book_sale_cost");
                    break;
                case "high-price":
                    sort = Sort.by(Sort.Direction.DESC, "book_sale_cost");
                    break;
                case "rate":
                    sort = Sort.by(Sort.Direction.DESC, "avg_rate");
                    break;
                case "review":
                    sort = Sort.by(Sort.Direction.DESC, "book_review_count");
                    break;
                default:
                    sort = Sort.by(Sort.Direction.DESC, "book_view_count");
            }
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        }
        return elasticAdaptor.searchPaged(query, pageable);
    }
}
