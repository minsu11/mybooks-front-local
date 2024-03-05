package store.mybooks.front.admin.wrap.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
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
    @NotBlank
    @PositiveOrZero
    private Integer id;
    
    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    @NotBlank
    @PositiveOrZero
    @Max(100000)
    private Integer cost;
}
