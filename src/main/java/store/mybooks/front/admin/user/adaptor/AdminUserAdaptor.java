package store.mybooks.front.admin.user.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.auth.Annotation.RequiredAuthorization;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.pageable.dto.response.PageResponse;
import store.mybooks.front.user.dto.response.UserGetResponse;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.admin.member.adaptor<br>
 * fileName       : MemberAdaptor<br>
 * author         : masiljangajji<br>
 * date           : 3/15/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/15/24        masiljangajji       최초 생성
 */

@Component
@RequiredArgsConstructor
public class UserAdaptor {

    private final RestTemplate restTemplate;

    private final GatewayAdaptorProperties gatewayAdaptorProperties;
    private static final String ADMIN_URL = "/api/admin/users";

    @RequiredAuthorization
    public PageResponse<UserGetResponse> getPagedMembers(Pageable pageable) {

        ResponseEntity<PageResponse<UserGetResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + ADMIN_URL + "/page?page=" + pageable.getPageNumber()
                        + "&size=" + pageable.getPageSize(),
                HttpMethod.GET,
                new HttpEntity<>(Utils.getAuthHeader()),
                new ParameterizedTypeReference<>() {
                });

        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

}
