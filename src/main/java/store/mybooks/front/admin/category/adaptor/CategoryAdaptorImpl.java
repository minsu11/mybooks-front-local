package store.mybooks.front.admin.category.adaptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.category.model.request.CategoryCreateRequestForTransmission;
import store.mybooks.front.admin.category.model.request.CategoryModifyRequestForTransmission;
import store.mybooks.front.admin.category.model.response.CategoryGetResponse;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.pageable.dto.response.PageResponse;

/**
 * packageName    : store.mybooks.front.category.adaptor
 * fileName       : CategoryAdaptorImpl
 * author         : damho-lee
 * date           : 2/24/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/24/24          damho-lee          최초 생성
 */
@Component
@RequiredArgsConstructor
public class CategoryAdaptorImpl implements CategoryAdaptor {
    private final RestTemplate restTemplate;

    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    @Override
    public List<CategoryGetResponse> getHighestCategories() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List<CategoryGetResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + "/api/categories/highest",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return exchange.getBody();
    }

    @Override
    public List<CategoryGetResponse> getChildCategories(long parentCategoryId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List<CategoryGetResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + "/api/categories/parentCategoryId/" + parentCategoryId,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return exchange.getBody();
    }

    @Override
    public void createCategory(CategoryCreateRequestForTransmission categoryCreateRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<CategoryCreateRequestForTransmission> requestEntity =
                new HttpEntity<>(categoryCreateRequest, headers);

        ResponseEntity<Void> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + "/api/categories",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (exchange.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException();
        }
    }

    @Override
    public PageResponse<CategoryGetResponse> getCategories(int page, int size) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<PageResponse<CategoryGetResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + "/api/categories/page?page=" + page + "&size=" + size,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return exchange.getBody();
    }

    @Override
    public void updateCategory(Integer id, CategoryModifyRequestForTransmission categoryModifyRequestForTransmission) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<CategoryModifyRequestForTransmission> requestEntity =
                new HttpEntity<>(categoryModifyRequestForTransmission, headers);

        ResponseEntity<PageResponse<CategoryGetResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + "/api/categories/" + id,
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteCategory(Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<PageResponse<CategoryGetResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + "/api/categories/" + id,
                HttpMethod.DELETE,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }

    @Override
    public CategoryGetResponse getCategory(Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<CategoryGetResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + "/api/categories/id/" + id,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }

        return exchange.getBody();
    }
}
