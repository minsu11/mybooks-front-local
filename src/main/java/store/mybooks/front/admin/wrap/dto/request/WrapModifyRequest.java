package store.mybooks.front.admin.wrap.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.admin.wrap.dto.request<br>
 * fileName       : WrapModifyRequest<br>
 * author         : minsu11<br>
 * date           : 3/1/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/1/24        minsu11       최초 생성<br>
 */
@Getter
@AllArgsConstructor
public class WrapModifyRequest {
    private Integer id;
    private String name;
    private Integer cost;
}
