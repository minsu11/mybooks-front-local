package store.mybooks.front.pageable.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
@AllArgsConstructor
public class PageResponse<T> {
    private List<T> content;
    private Pageable pageable;
    private boolean last;
    private int totalPages;
    private int totalElements;
    private int size;
    private int number;
    private boolean first;
    private Sort sort;
    private int numberOfElements;
    private boolean empty;

}