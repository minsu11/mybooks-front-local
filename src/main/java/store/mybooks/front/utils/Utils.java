package store.mybooks.front.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * packageName    : store.mybooks.front.util<br>
 * fileName       : Util<br>
 * author         : minsu11<br>
 * date           : 2/27/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/27/24        minsu11       최초 생성<br>
 */
public class Utils {

    private Utils() {
    }

    public static <T> T getResponseEntity(RestTemplate restTemplate, String url,
                                          HttpMethod method, RequestEntity<T> requestEntity,
                                          HttpStatus status, Class<T> targetType,
                                          Object... id) {
        ObjectMapper mapper = new ObjectMapper();
        ResponseEntity<T> exchange = restTemplate.exchange(url,
                method,
                requestEntity,
                new ParameterizedTypeReference<T>() {
                }, id);

        if (exchange.getStatusCode() != status) {
            throw new RuntimeException();
        }

        return mapper.convertValue(exchange.getBody(), targetType);
    }


}
