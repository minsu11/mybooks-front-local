package store.mybooks.front.admin.book.model.response;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.admin.book.model.response <br/>
 * fileName       : BookDetailResponse<br/>
 * author         : newjaehun <br/>
 * date           : 2/26/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 2/26/24        newjaehun       최초 생성<br/>
 */
@Getter
@NoArgsConstructor
public class BookDetailResponse {
    private Long id;

    private String bookImage;

    private String name;

    private String authorName;

    private String publisherName;

    private LocalDate publishDate;

    private Integer saleCost;

    private Integer originalCost;

    private Double totalRate;

    private Integer reviewCount;

    private Boolean isPacking;

    private Integer page;

    private String isbn;

    private List<String> category;

    private List<String> tag;

    private Integer stock;

    private String index;

    private String content;

    private String bookContentImage;

//    private List<ReviewDetailResponse> review;
}
