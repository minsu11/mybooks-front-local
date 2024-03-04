package store.mybooks.front.admin.book_order.adaptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.book_order.dto.request.BookOrderRegisterInvoiceNumberRequest;
import store.mybooks.front.admin.book_order.dto.request.BookOrderStatusModifyRequest;
import store.mybooks.front.admin.book_order.dto.response.BookOrderAdminResponse;
import store.mybooks.front.admin.book_order.dto.response.BookOrderRegisterInvoiceNumberResponse;
import store.mybooks.front.admin.book_order.dto.response.BookOrderStatusModifyResponse;
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
@Slf4j
@Component
@RequiredArgsConstructor
public class BookOrderAdaptor {
    private final RestTemplate restTemplate;
    private final GatewayAdaptorProperties gatewayAdaptorProperties;
    private static final String URL = "/api/orders";

    /**
     * methodName : getAdminBookOrderList<br>
     * author : minsu11<br>
     * description : 관리자가 볼 페이징 된 회원의 주문 내역.
     * <br> *
     *
     * @param pageable 페이징
     * @return page
     */
    public PageResponse<BookOrderAdminResponse> getAdminBookOrderList(Pageable pageable) {
        ResponseEntity<PageResponse<BookOrderAdminResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/admin?page=" + pageable.getPageNumber() + "&size=" + pageable.getPageSize(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageResponse<BookOrderAdminResponse>>() {
                });

        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }


    /**
     * methodName : modifyOrderStatus<br>
     * author : minsu11<br>
     * description : 관리자가 주문 상태를 변경
     * <br> *
     *
     * @param request
     * @return book order status modify response
     */
    public BookOrderStatusModifyResponse modifyOrderStatus(BookOrderStatusModifyRequest request) {
        HttpHeaders headers = Utils.getHttpHeader();
        HttpEntity<BookOrderStatusModifyRequest> responseHttpEntity = new HttpEntity<>(request, headers);

        ResponseEntity<BookOrderStatusModifyResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/admin/status",
                HttpMethod.PUT,
                responseHttpEntity,
                new ParameterizedTypeReference<BookOrderStatusModifyResponse>() {
                });
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

    public BookOrderRegisterInvoiceNumberResponse registerInvoiceNumberResponse(BookOrderRegisterInvoiceNumberRequest request) {
        HttpHeaders headers = Utils.getHttpHeader();
        HttpEntity<BookOrderRegisterInvoiceNumberRequest> requestHttpEntity = new HttpEntity<>(request, headers);
        ResponseEntity<BookOrderRegisterInvoiceNumberResponse> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + URL + "/admin/invoiceNumber",
                HttpMethod.PUT,
                requestHttpEntity,
                new ParameterizedTypeReference<BookOrderRegisterInvoiceNumberResponse>() {
                });

        log.info("exchange value: {}", exchange.getBody());
        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }

}
