package store.mybooks.front.oauth2;

import java.security.Provider;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import store.mybooks.front.config.KeyConfig;

/**
 * packageName    : store.mybooks.front.oauth2<br>
 * fileName       : OauthProperties<br>
 * author         : masiljangajji<br>
 * date           : 3/3/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/3/24        masiljangajji       최초 생성
 */

@Getter
@ConfigurationProperties(prefix = "oauth2")
public class OauthProperties {

    private final Map<String, User> user = new HashMap<>();

    private final Map<String, Provider> provider = new HashMap<>();

    @Getter
    @Setter
    public static class User {
        private String clientId;
        private String clientSecret;
        private String redirectUri;
    }

    @Getter
    @Setter
    public static class Provider {
        private String tokenUri;
        private String userInfoUri;
    }
}
