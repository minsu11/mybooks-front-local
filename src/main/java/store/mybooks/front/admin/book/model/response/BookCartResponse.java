package store.mybooks.front.admin.book.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.admin.book.model.response <br/>
 * fileName       : BookCartResponse<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/7/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/7/24        Fiat_lux       최초 생성<br/>
 */
@Getter
@NoArgsConstructor
public class BookCartResponse {
    private Long id;
    private String name;
    private String bookImage;
    private Integer saleCost;
}