package store.mybooks.front.admin.book_order.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.admin.book_order.dto.response.BookOrderAdminResponse;
import store.mybooks.front.config.GatewayAdaptorProperties;
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
    private static final String ADMIN_URL = "/admin/order";

    /**
     * methodName : getAdminBookOrderList<br>
     * author : minsu11<br>
     * description : 관리자가 볼 페이징 된 회원의 주문 내역.
     * <br> *
     *
     * @param pageable 페이징
     * @return page
     */
    public Page<BookOrderAdminResponse> getAdminBookOrderList(Pageable pageable) {
        ResponseEntity<Page<BookOrderAdminResponse>> exchange = restTemplate.exchange(
                gatewayAdaptorProperties.getAddress() + ADMIN_URL + "/admin?page={page}&size={size}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Page<BookOrderAdminResponse>>() {
                }, pageable.getPageNumber(), pageable.getPageSize());

        return Utils.getResponseEntity(exchange, HttpStatus.OK);
    }


}
