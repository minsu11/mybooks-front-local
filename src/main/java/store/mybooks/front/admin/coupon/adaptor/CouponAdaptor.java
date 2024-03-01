package store.mybooks.front.admin.coupon.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.coupon.model.request.BookFlatDiscountCouponCreateRequest;
import store.mybooks.front.admin.coupon.model.request.BookPercentageCouponCreateRequest;
import store.mybooks.front.admin.coupon.model.request.CategoryFlatDiscountCouponCreateRequest;
import store.mybooks.front.admin.coupon.model.request.CategoryPercentageCouponCreateRequest;
import store.mybooks.front.admin.coupon.model.request.FlatDiscountCouponCreateRequest;
import store.mybooks.front.admin.coupon.model.request.TotalPercentageCouponCreateRequest;
import store.mybooks.front.config.GatewayAdaptorProperties;
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

    public void createTotalPercentageCoupon(TotalPercentageCouponCreateRequest createRequest) {
        HttpEntity<TotalPercentageCouponCreateRequest> requestEntity =
                new HttpEntity<>(createRequest, Utils.getHttpHeader());

        ResponseEntity<Void> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/total-percentage-coupon/register",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        Utils.getResponseEntity(exchange, HttpStatus.CREATED);
    }

    public void createFlatDiscountCoupon(FlatDiscountCouponCreateRequest createRequest) {
        HttpEntity<FlatDiscountCouponCreateRequest> requestEntity =
                new HttpEntity<>(createRequest, Utils.getHttpHeader());

        ResponseEntity<Void> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/flat-discount-coupon/register",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        Utils.getResponseEntity(exchange, HttpStatus.CREATED);
    }

    public void createBookPercentageCoupon(BookPercentageCouponCreateRequest createRequest) {
        HttpEntity<BookPercentageCouponCreateRequest> requestEntity =
                new HttpEntity<>(createRequest, Utils.getHttpHeader());

        ResponseEntity<Void> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/book-percentage-coupon/register",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        Utils.getResponseEntity(exchange, HttpStatus.CREATED);
    }

    public void createBookFlatDiscountCoupon(BookFlatDiscountCouponCreateRequest createRequest) {
        HttpEntity<BookFlatDiscountCouponCreateRequest> requestEntity =
                new HttpEntity<>(createRequest, Utils.getHttpHeader());

        ResponseEntity<Void> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/book-flat-discount-coupon/register",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        Utils.getResponseEntity(exchange, HttpStatus.CREATED);
    }

    public void createCategoryPercentageCoupon(CategoryPercentageCouponCreateRequest createRequest) {
        HttpEntity<CategoryPercentageCouponCreateRequest> requestEntity =
                new HttpEntity<>(createRequest, Utils.getHttpHeader());

        ResponseEntity<Void> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/category-percentage-coupon/register",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        Utils.getResponseEntity(exchange, HttpStatus.CREATED);
    }

    public void createCategoryFlatDiscountCoupon(CategoryFlatDiscountCouponCreateRequest createRequest) {
        HttpEntity<CategoryFlatDiscountCouponCreateRequest> requestEntity =
                new HttpEntity<>(createRequest, Utils.getHttpHeader());

        ResponseEntity<Void> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/category-flat-discount-coupon/register",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        Utils.getResponseEntity(exchange, HttpStatus.CREATED);
    }

}
