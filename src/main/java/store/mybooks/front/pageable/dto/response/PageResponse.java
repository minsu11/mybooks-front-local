package store.mybooks.front.pageable.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.pageable.dto.response<br>
 * fileName       : PageResponse<br>
 * author         : minsu11<br>
 * date           : 2/24/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/24/24        minsu11       최초 생성<br>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private List<T> content;
    @JsonProperty("pageable")
    private Pageable pageable;
    private boolean last;
    private int totalPages;
    private int totalElements;
    private int size;
    private int number;
    private boolean first;
    @JsonProperty("sort")
    private Sort sort;
    private int numberOfElements;
    private boolean empty;
}
