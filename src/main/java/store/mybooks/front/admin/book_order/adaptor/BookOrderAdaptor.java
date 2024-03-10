package store.mybooks.front.admin.book_order.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import store.mybooks.front.admin.book_order.dto.request.BookOrderRegisterInvoiceNumberRequest;
import store.mybooks.front.admin.book_order.dto.request.BookOrderStatusModifyRequest;
import store.mybooks.front.admin.book_order.dto.response.BookOrderAdminResponse;
import store.mybooks.front.admin.book_order.dto.response.BookOrderRegisterInvoiceNumberResponse;
import store.mybooks.front.admin.book_order.dto.response.BookOrderStatusModifyResponse;
import store.mybooks.front.auth.Annotation.RequiredAuthorization;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.pageable.dto.response.PageResponse;
import store.mybooks.front.utils.Utils;

/**
 * packageName    : store.mybooks.front.admin.book_order.adaptor<br>
 * fileName       : BookOrderAdaptor<br>
 * author         : minsu11<br>
 * date           : 3/3/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/3/24        minsu11       최초 생성<br>
 */
@Component
@RequiredArgsConstructor
public class BookOrderAdaptor {
    private final RestTemplate restTemplate;
    private final GatewayAdaptorProperties gatewayAdaptorProperties;
    private static final String URL = "/api/orders";
    private static final String MEMBER_URL = "/api/member/orders";
    private static final String ADMIN_URL = "/api/admin/orders";

    /**
     * methodName : getAdminBookOrderList<br>
     * author : minsu11<br>
     * description : 관리자가 볼 페이징 된 회원의 주문 내역.
     * <br> *
     *
     * @param pageable 페이징
     * @return page
     */
    @RequiredAuthorization
    public PageResponse<BookOrderAdminResponse> getAdminBookOrderList(Pageable pageable) {
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(gatewayAdaptorProperties.getAddress() + MEMBER_URL + "/{admin}")
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .encode()
                .buildAndExpand("admin");

        ResponseEntity<PageResponse<BookOrderAdminResponse>> exchange = restTemplate.exchange(
                uriComponents.toString(),
                HttpMethod.GET,
                new HttpEntity<>(Utils.getAuthHeader()),
                new ParameterizedTypeReference<PageResponse<BookOrderAdminResponse>>() {
                });

        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }


    /**
     * methodName : modifyOrderStatus<br>
     * author : minsu11<br>
     * description : 관리자가 주문 상태를 변경.
     * <br> *
     *
     * @param request 주문 상태 수정 dto
     * @return book order status modify response
     */
    @RequiredAuthorization
    public BookOrderStatusModifyResponse modifyOrderStatus(BookOrderStatusModifyRequest request) {

        HttpHeaders headers = Utils.getAuthHeader();
        HttpEntity<BookOrderStatusModifyRequest> responseHttpEntity = new HttpEntity<>(request, headers);

        ResponseEntity<BookOrderStatusModifyResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + ADMIN_URL + "/statuses",
                HttpMethod.PUT,
                responseHttpEntity,
                new ParameterizedTypeReference<BookOrderStatusModifyResponse>() {
                });
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    /**
     * methodName : registerInvoiceNumberResponse<br>
     * author : minsu11<br>
     * description : 송장 번호 등록 요청.
     * <br> *
     *
     * @param request 등록할 송장 번호
     * @return book order register invoice number response
     */
    @RequiredAuthorization
    public BookOrderRegisterInvoiceNumberResponse registerInvoiceNumberResponse(BookOrderRegisterInvoiceNumberRequest request) {
        HttpHeaders headers = Utils.getAuthHeader();
        HttpEntity<BookOrderRegisterInvoiceNumberRequest> requestHttpEntity = new HttpEntity<>(request, headers);
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(gatewayAdaptorProperties.getAddress() + ADMIN_URL + "/{value}/{status}")
                .encode()
                .buildAndExpand("admin", "invoiceNumber");

        ResponseEntity<BookOrderRegisterInvoiceNumberResponse> exchange = restTemplate.exchange(
                uriComponents.toString(),
                HttpMethod.PUT,
                requestHttpEntity,
                new ParameterizedTypeReference<BookOrderRegisterInvoiceNumberResponse>() {
                });

        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

}
