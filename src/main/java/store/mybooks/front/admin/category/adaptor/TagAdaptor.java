package store.mybooks.front.admin.category.adaptor;

import store.mybooks.front.admin.category.model.request.TagCreateRequest;
import store.mybooks.front.admin.category.model.request.TagModifyRequest;
import store.mybooks.front.admin.category.model.response.TagGetResponse;
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

    void createTag(TagCreateRequest tagCreateRequest);

    void updateTag(Integer id, TagModifyRequest tagModifyRequest);

    void deleteTag(Integer id);

}
