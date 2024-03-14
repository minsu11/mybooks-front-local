package store.mybooks.front.admin.book.model.response;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import store.mybooks.front.image.dto.ImageResponse;

/**
 * packageName    : store.mybooks.front.admin.book.model.response
 * fileName       : BookBriefResponseIncludePublishDate
 * author         : damho-lee
 * date           : 3/14/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/14/24          damho-lee          최초 생성
 */
@Getter
@NoArgsConstructor
public class BookBriefResponseIncludePublishDate {
    private Long id;

    private ImageResponse imageResponse;

    private String name;

//    private Double rate;

    private Integer cost;

    private Integer saleCost;

    private LocalDate publishDate;
}
