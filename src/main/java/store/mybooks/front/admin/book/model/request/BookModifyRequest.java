package store.mybooks.front.admin.book.model.request;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.admin.book.model.request <br/>
 * fileName       : BookModifyRequest<br/>
 * author         : newjaehun <br/>
 * date           : 2/26/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 2/26/24        newjaehun       최초 생성<br/>
 */
@Getter
@AllArgsConstructor
public class BookModifyRequest {
    @NotNull
    @Positive
    private Integer saleCost;
    @NotBlank
    @Size(min = 1, max = 20)
    private String bookStatusId;
    @NotNull
    @PositiveOrZero
    private Integer stock;
    @NotNull
    private Boolean isPacking;

    @NotNull
    @Size(min = 1)
    private List<Integer> categoryList;

    private List<Integer> tagList;
}
