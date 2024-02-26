package store.mybooks.front.user_address.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.resource.user_address.dto.request
 * fileName       : UserAddressCreateRequest
 * author         : masiljangajji
 * date           : 2/13/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/13/24        masiljangajji       최초 생성
 */
@Getter
@AllArgsConstructor
public class UserAddressCreateRequest {

    private String alias;

    private String roadName;

    private String detail;

    private Integer number;

    private String reference;
}
