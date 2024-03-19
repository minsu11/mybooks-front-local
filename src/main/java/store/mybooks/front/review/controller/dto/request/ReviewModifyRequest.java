package store.mybooks.front.review.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : store.mybooks.resource.review.dto.reqeust<br>
 * fileName       : ReviewModifyRequest<br>
 * author         : masiljangajji<br>
 * date           : 3/17/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/17/24        masiljangajji       최초 생성
 */
@Getter
@Setter
@AllArgsConstructor
public class ReviewModifyRequest {

    private Integer rate;

    private String title;

    private String content;

}
