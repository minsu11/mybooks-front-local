package store.mybooks.front.user_address.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.resource.user_address.dto.response<br>
 * fileName       : UserAddressGetResponse<br>
 * author         : masiljangajji<br>
 * date           : 2/19/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/19/24        masiljangajji       최초 생성
 */
@Getter
@AllArgsConstructor
public class UserAddressGetResponse {

    private Long id;

    private String alias;

    private String roadName;

    private String detail;

    private Integer number;

    private String reference;

}
