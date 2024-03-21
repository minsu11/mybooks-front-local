package store.mybooks.front.user_address.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : store.mybooks.resource.user_address.dto.request<br>
 * fileName       : UserAddressModifyRequest<br>
 * author         : masiljangajji<br>
 * date           : 2/13/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/13/24        masiljangajji       최초 생성
 */

@Getter
@AllArgsConstructor
public class UserAddressModifyRequest {

    private String alias;

    private String detail;

    private String reference;


}
