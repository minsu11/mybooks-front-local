package store.mybooks.front.pageable.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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


class Pageable {

    @JsonIgnore
    Sort sort;

    @JsonProperty("offset")
    private int offset;

    @JsonProperty("pageNumber")
    private int pageNumber;

    @JsonProperty("pageSize")
    private int pageSize;

    @JsonProperty(value = "paged")
    private boolean paged;


    @JsonProperty(value = "unpaged")
    private boolean unPaged;

}


