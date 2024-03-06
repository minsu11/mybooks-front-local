package store.mybooks.front.oauth2;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.mybooks.front.config.KeyConfig;

/**
 * packageName    : store.mybooks.front.oauth2<br>
 * fileName       : OauthAdaptor<br>
 * author         : masiljangajji<br>
 * date           : 3/3/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/3/24        masiljangajji       최초 생성
 */

public class OauthAdapter {

    // OauthProperties를 OauthProvider로 변환해준다.
    public static Map<String, OauthProvider> getOauthProviders(OauthProperties properties) {
        Map<String, OauthProvider> oauthProvider = new HashMap<>();

        properties.getUser().forEach((key, value) -> oauthProvider.put(key,
                new OauthProvider(value, properties.getProvider().get(key))));

        return oauthProvider;
    }
}