package store.mybooks.front.oauth;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * methodName : getOauthProviders
     * author : masiljangajji
     * description : OauthProperties를 OauthProvider로 변환
     *
     * @param properties OauthProperties cliendId , cliendSecrect
     * @return map
     */
    public static Map<String, OauthProvider> getOauthProviders(OauthProperties properties) {
        Map<String, OauthProvider> oauthProvider = new HashMap<>();

        properties.getUser().forEach((key, value) -> oauthProvider.put(key,
                new OauthProvider(value, properties.getProvider().get(key))));

        return oauthProvider;
    }
}