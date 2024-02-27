package store.mybooks.front.pageable.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.front.pageable.dto.response<br>
 * fileName       : Pageable<br>
 * author         : minsu11<br>
 * date           : 2/24/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/24/24        minsu11       최초 생성<br>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
class Pageable {
    Sort sort;
    private int offset;
    @JsonProperty("pageNumber")
    private int pageNumber;
    @JsonProperty("pageSize")
    private int pageSize;
    @JsonProperty("paged")
    private boolean paged;
    @JsonProperty("unpaged")
    private boolean unPaged;

}


