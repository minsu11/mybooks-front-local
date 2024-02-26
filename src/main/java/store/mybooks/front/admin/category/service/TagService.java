package store.mybooks.front.admin.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.category.adaptor.TagAdaptor;
import store.mybooks.front.admin.category.model.request.TagCreateRequest;
import store.mybooks.front.admin.category.model.response.TagGetResponse;
import store.mybooks.front.pageable.dto.response.PageResponse;

/**
 * packageName    : store.mybooks.front.category.service
 * fileName       : TagService
 * author         : damho-lee
 * date           : 2/26/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/26/24          damho-lee          최초 생성
 */
@Service
@RequiredArgsConstructor
public class TagService {
    private final TagAdaptor tagAdaptor;

    /**
     * methodName : getTags <br>
     * author : damho-lee <br>
     * description : page, size 에 맞는 태그들을 찾는 메서드.<br>
     *
     * @param page int
     * @param size int
     * @return pageResponse
     */
    public PageResponse<TagGetResponse> getTags(int page, int size) {
        return tagAdaptor.getTags(page, size);
    }

    /**
     * methodName : createTag <br>
     * author : damho-lee <br>
     * description : 태그 생성 메서드.<br>
     *
     * @param tagCreateRequest TagCreateRequest
     */
    public void createTag(TagCreateRequest tagCreateRequest) {
        tagAdaptor.createTag(tagCreateRequest);
    }
}
