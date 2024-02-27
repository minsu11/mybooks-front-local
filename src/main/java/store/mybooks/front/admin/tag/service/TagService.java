package store.mybooks.front.admin.tag.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.tag.adaptor.TagAdaptor;
import store.mybooks.front.admin.tag.model.request.TagCreateRequest;
import store.mybooks.front.admin.tag.model.request.TagModifyRequest;
import store.mybooks.front.admin.tag.model.response.TagGetResponse;
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

    public TagGetResponse getTag(Integer id) {
        return tagAdaptor.getTag(id);
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

    public void updateTag(Integer id, TagModifyRequest tagModifyRequest) {
        tagAdaptor.updateTag(id, tagModifyRequest);
    }

    public void deleteTag(Integer id) {
        tagAdaptor.deleteTag(id);
    }
}
