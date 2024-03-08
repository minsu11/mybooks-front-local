package store.mybooks.front.pageable.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private boolean last;

    private int totalPages;

    private int totalElements;

    @JsonIgnore
    private boolean first;
    @JsonIgnore
    private int size;
    private int number;

    @JsonIgnore
    private Sort sort;
    @JsonIgnore
    private int numberOfElements;
    @JsonIgnore
    private boolean empty;
}
