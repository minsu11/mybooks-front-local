package store.mybooks.front.admin.book.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.resource.book.dto.response <br/>
 * fileName       : BookPopularityResponse<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/21/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/21/24        Fiat_lux       최초 생성<br/>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookPopularityResponse {
    private Long id;
    private String image;
    private String name;
    private Long reviewCount;
    private Integer cost;
    private Integer saleCost;
    private Double rate;
    private Integer viewCount;
}
