package store.mybooks.front.pageable.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.pageable.dto.response<br>
 * fileName       : Sort<br>
 * author         : minsu11<br>
 * date           : 2/24/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/24/24        minsu11       최초 생성<br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sort {
    @JsonProperty("empty")
    private boolean empty;
    @JsonProperty("sorted")
    private boolean sorted;
    @JsonProperty("unsorted")
    private boolean unsorted;

}
