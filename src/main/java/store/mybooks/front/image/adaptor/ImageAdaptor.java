package store.mybooks.front.image.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.mybooks.front.config.GatewayAdaptorProperties;
import store.mybooks.front.image.dto.ImageGetResponse;

/**
 * packageName    : store.mybooks.front.image.adaptor <br/>
 * fileName       : ImageAdaptor<br/>
 * author         : Fiat_lux <br/>
 * date           : 2/28/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 2/28/24        Fiat_lux       최초 생성<br/>
 */
@Component
@RequiredArgsConstructor
public class ImageAdaptor {
    private final RestTemplate restTemplate;
    private final GatewayAdaptorProperties gatewayAdaptorProperties;

    public ImageGetResponse getImage(Long id) {
        String url = gatewayAdaptorProperties.getAddress() + "/api/images/{imageId}";

        ResponseEntity<ImageGetResponse> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null, ImageGetResponse.class, id);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("");
        }
    }

    public void deleteImage(Long id) {
        String url = gatewayAdaptorProperties.getAddress() + "/api/images/{imageId}";

        ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class, id);
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("");
        }
    }
}
