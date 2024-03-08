package store.mybooks.front.admin.wrap.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.admin.wrap.dto.response<br>
 * fileName       : WrapModifyResponse<br>
 * author         : minsu11<br>
 * date           : 3/1/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/1/24        minsu11       최초 생성<br>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WrapModifyResponse {
    private String name;
    private Integer cost;
}
