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

    @Scheduled(cron = "0 0 1 * * *")
    public void fetchAndCacheCategoryList() {
        redisTemplate.delete(ALL_CATEGORIES_KEY);

        List<CategoryGetResponseForMainView> categoryList = categoryAdaptor.getCategoriesForMainView();
        redisTemplate.opsForValue().set(ALL_CATEGORIES_KEY, categoryList);
    }

    public List<CategoryGetResponseForMainView> getCategoriesForMainView() {
        List<CategoryGetResponseForMainView> categoryList
                = (List<CategoryGetResponseForMainView>) redisTemplate.opsForValue().get(ALL_CATEGORIES_KEY);

        if (categoryList == null) {
            fetchAndCacheCategoryList();
            categoryList = (List<CategoryGetResponseForMainView>) redisTemplate.opsForValue().get(ALL_CATEGORIES_KEY);
        }

        return categoryList;
    }

    public CategoryGetResponseForCategoryView getCategoriesForCategoryView(Integer categoryId) {
        return categoryAdaptor.getCategoriesForCategoryView(categoryId);
    }

    public PageResponse<BookBriefResponse> getBooksForCategoryView(Integer categoryId, Pageable pageable) {
        return categoryAdaptor.getBooksForCategoryView(categoryId, pageable);
    }
}
