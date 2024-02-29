package store.mybooks.front.admin.tag.adaptor;

import java.util.List;
import store.mybooks.front.admin.tag.model.request.TagCreateRequest;
import store.mybooks.front.admin.tag.model.request.TagModifyRequest;
import store.mybooks.front.admin.tag.model.response.TagGetResponse;
import store.mybooks.front.pageable.dto.response.PageResponse;

/**
 * packageName    : store.mybooks.front.category.adaptor
 * fileName       : TagAdaptor
 * author         : damho-lee
 * date           : 2/26/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/26/24          damho-lee          최초 생성
 */
public interface TagAdaptor {
    TagGetResponse getTag(Integer id);

    PageResponse<TagGetResponse> getTags(int page, int size);

    List<TagGetResponse> getTags();

    void createTag(TagCreateRequest tagCreateRequest);

    void updateTag(Integer id, TagModifyRequest tagModifyRequest);

    void deleteTag(Integer id);

}
