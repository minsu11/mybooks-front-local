package store.mybooks.front.book.author.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.book.author.dto.response<br>
 * fileName       : AuthorResponse<br>
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
public class AuthorPageResponse {
    private List<AuthorResponse> content;
    private Pageable pageable;
    private boolean last;
    private int totalPages;
    private int totalElements;
    private int size;
    private int number;
    private boolean first;
    private Sorting sort;
    private int numberOfElements;
    private boolean empty;

    // Getters and setters
}

class Pageable {
    private Sort sort;
    private int offset;
    @JsonProperty("pageNumber")
    private int pageNumber;
    @JsonProperty("pageSize")
    private int pageSize;
    private boolean paged;
    private boolean unpaged;

    // Getters and setters
}

class Sort {
    private boolean empty;
    private boolean sorted;
    private boolean unsorted;

    // Getters and setters
}

class AuthorResponse {
    private String name;
    private int id;
    private String content;

    // Getters and setters
}