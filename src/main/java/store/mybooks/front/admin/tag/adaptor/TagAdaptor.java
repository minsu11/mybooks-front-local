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
public class TagAdaptor {
    private final RestTemplate restTemplate;

    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    private static final String URL = "/api/tags/";

    /**
     * methodName : getTag <br>
     * author : damho-lee <br>
     * description : tag 가져오기.<br>
     *
     * @param id Integer
     * @return tagGetResponse TagGetResponse
     */
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

    /**
     * methodName : getTags <br>
     * author : damho-lee <br>
     * description : tag page 요청.<br>
     *
     * @param pageable Pageable
     * @return PageResponse
     */
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

    /**
     * methodName : getTags <br>
     * author : damho-lee <br>
     * description : 태그 리스트 요청.<br>
     *
     * @return list
     */
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

    /**
     * methodName : createTag <br>
     * author : damho-lee <br>
     * description : 태그 생성.<br>
     *
     * @param tagCreateRequest TagCreateRequest
     */
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

    /**
     * methodName : updateTag <br>
     * author : damho-lee <br>
     * description : 태그 수정.<br>
     *
     * @param id Integer
     * @param tagModifyRequest TagModifyRequest
     */
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

    /**
     * methodName : deleteTag <br>
     * author : damho-lee <br>
     * description : 태그 삭제.<br>
     *
     * @param id Integer
     */
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
