package store.mybooks.front.category.adaptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.book.model.response.BookBriefResponse;
import store.mybooks.front.category.model.reesponse.CategoryGetResponseForCategoryView;
import store.mybooks.front.category.model.reesponse.CategoryGetResponseForMainView;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.pageable.dto.response.PageResponse;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.category.adaptor
 * fileName       : CategoryAdaptor
 * author         : damho-lee
 * date           : 3/13/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/13/24          damho-lee          최초 생성
 */
@Component
@RequiredArgsConstructor
public class CategoryAdaptor {
    private final RestTemplate restTemplate;

    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    private static final String URL = "/api/categories";

    public List<CategoryGetResponseForMainView> getCategoriesForMainView() {
        HttpHeaders headers = Utils.getHttpHeader();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List<CategoryGetResponseForMainView>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/main",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    public CategoryGetResponseForCategoryView getCategoriesForCategoryView(Integer categoryId) {
        HttpHeaders headers = Utils.getHttpHeader();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<CategoryGetResponseForCategoryView> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/view/" + categoryId,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    public PageResponse<BookBriefResponse> getBooksForCategoryView(Integer categoryId, Pageable pageable) {
        HttpHeaders headers = Utils.getHttpHeader();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<PageResponse<BookBriefResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/view/book/" + categoryId
                        + "?page=" + pageable.getPageNumber() + "&size=" + pageable.getPageSize(),
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

}
