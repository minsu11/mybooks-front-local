package store.mybooks.front.admin.tag.adaptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.tag.model.request.TagCreateRequest;
import store.mybooks.front.admin.tag.model.request.TagModifyRequest;
import store.mybooks.front.admin.tag.model.response.TagGetResponse;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.pageable.dto.response.PageResponse;

/**
 * packageName    : store.mybooks.front.category.adaptor
 * fileName       : TagAdaptorImpl
 * author         : damho-lee
 * date           : 2/26/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/26/24          damho-lee          최초 생성
 */
@Component
@RequiredArgsConstructor
public class TagAdaptorImpl implements TagAdaptor {
    private final RestTemplate restTemplate;

    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    private static final String URL = "/api/tags/";

    @Override
    public TagGetResponse getTag(Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<TagGetResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + id,
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
    public PageResponse<TagGetResponse> getTags(Pageable pageable) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<PageResponse<TagGetResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/page?page=" + pageable.getPageNumber() + "&size="
                        + pageable.getPageSize(),
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
    public List<TagGetResponse> getTags() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List<TagGetResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL,
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
    public void createTag(TagCreateRequest tagCreateRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<TagCreateRequest> requestEntity = new HttpEntity<>(tagCreateRequest, headers);

        ResponseEntity<Void> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (exchange.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateTag(Integer id, TagModifyRequest tagModifyRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<TagModifyRequest> requestEntity = new HttpEntity<>(tagModifyRequest, headers);

        ResponseEntity<Void> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/" + id,
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteTag(Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Void> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/" + id,
                HttpMethod.DELETE,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }
}
