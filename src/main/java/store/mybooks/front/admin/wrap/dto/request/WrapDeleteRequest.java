package store.mybooks.front.admin.wrap.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.admin.wrap.dto.request<br>
 * fileName       : WrapDeleteRequest<br>
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
public class WrapDeleteRequest {
    @NotBlank
    @PositiveOrZero
    private Integer id;
}
