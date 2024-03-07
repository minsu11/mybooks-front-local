package store.mybooks.front.cart.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.mybooks.front.cart.domain.CartDetail;

/**
 * packageName    : store.mybooks.front.cart <br/>
 * fileName       : CartService<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/3/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/3/24        Fiat_lux       최초 생성<br/>
 */
@Service
@RequiredArgsConstructor
public class CartUtil {
    public static final String CART_COOKIE = "cart";
    public final ObjectMapper objectMapper;


    public List<CartDetail> viewCart(Cookie cartCookie) throws JsonProcessingException {
        List<CartDetail> cartDetailList = new ArrayList<>();
        if (Objects.nonNull(cartCookie)) {
            String jsonObject = cartCookie.getValue();
            String decodeJsonObject = URLDecoder.decode(jsonObject, StandardCharsets.UTF_8);
            cartDetailList = Arrays.asList(objectMapper.readValue(decodeJsonObject, CartDetail[].class));
        }

        return cartDetailList;
    }
}
