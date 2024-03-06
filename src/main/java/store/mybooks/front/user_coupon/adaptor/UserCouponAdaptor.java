package store.mybooks.front.user_coupon.adaptor;

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
import store.mybooks.front.user_coupon.model.response.UserCouponGetResponse;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.user_coupon.adaptor
 * fileName       : UserCouponAdaptor
 * author         : damho-lee
 * date           : 3/5/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/5/24          damho-lee          최초 생성
 */
@Component
@RequiredArgsConstructor
public class UserCouponAdaptor {
    private final RestTemplate restTemplate;

    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    private static final String URL_USER = "/api/member/user-coupon";

    @RequiredAuthorization
    public PageResponse<UserCouponGetResponse> getUserCoupons(Pageable pageable) {
        HttpEntity<Void> requestEntity = new HttpEntity<>(Utils.getAuthHeader());

        ResponseEntity<PageResponse<UserCouponGetResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL_USER + "/page?page=" + pageable.getPageNumber()
                        + "&size=" + pageable.getPageSize(),
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {}
        );

        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

}
