package store.mybooks.front.admin.book.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import store.mybooks.front.image.dto.ImageResponse;

/**
 * packageName    : store.mybooks.front.admin.book.model.response <br/>
 * fileName       : BookBriefResponse<br/>
 * author         : newjaehun <br/>
 * date           : 2/26/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 2/26/24        newjaehun       최초 생성<br/>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookBriefResponse {
    private Long id;

    private ImageResponse imageResponse;

    private String name;

//    private Double rate;

    private Integer cost;

    private Integer saleCost;
}
