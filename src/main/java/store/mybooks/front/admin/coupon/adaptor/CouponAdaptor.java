package store.mybooks.front.admin.coupon.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.coupon.model.request.CouponCreateRequest;
import store.mybooks.front.admin.coupon.model.response.CouponGetResponse;
import store.mybooks.front.auth.Annotation.RequiredAuthorization;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.pageable.dto.response.PageResponse;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.admin.coupon.adaptor
 * fileName       : CouponAdaptorImpl
 * author         : damho-lee
 * date           : 3/1/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/1/24          damho-lee          최초 생성
 */
@Component
@RequiredArgsConstructor
public class CouponAdaptor {
    private final RestTemplate restTemplate;

    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    private static final String URL = "/api/coupons";
    private static final String URL_ADMIN = "/api/admin/coupons";

    /**
     * methodName : getCouponPage <br>
     * author : damho-lee <br>
     * description : 쿠폰 페이지 출력.<br>
     *
     * @param pageable Pageable
     * @return PageResponse response
     */
    @RequiredAuthorization
    public PageResponse<CouponGetResponse> getCouponPage(Pageable pageable) {
        HttpEntity<Void> requestEntity = new HttpEntity<>(Utils.getHttpHeader());

        ResponseEntity<PageResponse<CouponGetResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL_ADMIN + "/page?page="
                        + pageable.getPageNumber() + "&size=" + pageable.getPageSize(),
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {}
        );

        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    /**
     * methodName : createCoupon <br>
     * author : damho-lee <br>
     * description : 쿠폰 생성.<br>
     *
     * @param createRequest CouponCreateRequest
     */
    @RequiredAuthorization
    public void createCoupon(CouponCreateRequest createRequest) {
        HttpEntity<CouponCreateRequest> requestEntity = new HttpEntity<>(createRequest, Utils.getHttpHeader());

        ResponseEntity<Void> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL_ADMIN,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {}
        );

        Utils.getResponseEntity(exchange, HttpStatus.CREATED);
    }

    /**
     * methodName : deleteCoupon <br>
     * author : damho-lee <br>
     * description : 쿠폰 삭제.<br>
     *
     * @param id Long
     */
    @RequiredAuthorization
    public void deleteCoupon(Long id) {
        HttpEntity<Void> requestEntity = new HttpEntity<>(Utils.getHttpHeader());

        ResponseEntity<Void> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL_ADMIN + "/" + id,
                HttpMethod.DELETE,
                requestEntity,
                new ParameterizedTypeReference<>() {}
        );

        Utils.getResponseEntity(exchange, HttpStatus.OK);
    }
}
