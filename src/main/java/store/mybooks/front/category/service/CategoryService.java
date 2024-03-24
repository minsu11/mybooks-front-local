package store.mybooks.front.category.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.book.model.response.BookBriefResponse;
import store.mybooks.front.category.adaptor.CategoryAdaptor;
import store.mybooks.front.category.model.reesponse.CategoryGetResponseForCategoryView;
import store.mybooks.front.category.model.reesponse.CategoryGetResponseForMainView;
import store.mybooks.front.pageable.dto.response.PageResponse;

/**
 * packageName    : store.mybooks.front.category.service
 * fileName       : CategoryService
 * author         : damho-lee
 * date           : 3/13/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/13/24          damho-lee          최초 생성
 */
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryAdaptor categoryAdaptor;
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String ALL_CATEGORIES_KEY = "main:all_categories";

    /**
     * methodName : fetchAndCacheCategoryList <br>
     * author : damho-lee <br>
     * description : 매일 오전 1시에 All Categories 에 보여질 카테고리 리스트 가져오기.<br>
     */
    @Scheduled(cron = "0 0 1 * * *")
    public void fetchAndCacheCategoryList() {
        redisTemplate.delete(ALL_CATEGORIES_KEY);

        List<CategoryGetResponseForMainView> categoryList = categoryAdaptor.getCategoriesForMainView();
        redisTemplate.opsForValue().set(ALL_CATEGORIES_KEY, categoryList);
    }

    /**
     * methodName : getCategoriesForMainView <br>
     * author : damho-lee <br>
     * description : 메인 페이지에서 All Categories 에 보여질 카테고리 리스트 redis 에서 조회하기.<br>
     *
     * @return list
     */
    public List<CategoryGetResponseForMainView> getCategoriesForMainView() {
        List<CategoryGetResponseForMainView> categoryList
                = (List<CategoryGetResponseForMainView>) redisTemplate.opsForValue().get(ALL_CATEGORIES_KEY);

        if (categoryList == null) {
            fetchAndCacheCategoryList();
            categoryList = (List<CategoryGetResponseForMainView>) redisTemplate.opsForValue().get(ALL_CATEGORIES_KEY);
        }

        return categoryList;
    }

    /**
     * methodName : getCategoriesForCategoryView <br>
     * author : damho-lee <br>
     * description : 카테고리 선택 시 보여줄 정보 조회.<br>
     *
     * @param categoryId 카테고리 아이디
     * @return category get response for category view
     */
    public CategoryGetResponseForCategoryView getCategoriesForCategoryView(Integer categoryId) {
        return categoryAdaptor.getCategoriesForCategoryView(categoryId);
    }

    /**
     * methodName : getBooksForCategoryView <br>
     * author : damho-lee <br>
     * description : 카테고리 선택 시 보여줄 도서 조회.<br>
     *
     * @param categoryId 카테고리 아이디
     * @param pageable 페이지 정보
     * @return page response
     */
    public PageResponse<BookBriefResponse> getBooksForCategoryView(Integer categoryId, Pageable pageable) {
        return categoryAdaptor.getBooksForCategoryView(categoryId, pageable);
    }
}
