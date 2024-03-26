package store.mybooks.front.oauth.repository;

import java.util.HashMap;
import java.util.Map;
import store.mybooks.front.oauth.OauthProvider;

/**
 * packageName    : store.mybooks.front.oauth2<br>
 * fileName       : InMemoryProviderRepository<br>
 * author         : masiljangajji<br>
 * date           : 3/3/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/3/24        masiljangajji       최초 생성
 */

public class InMemoryProviderRepository {
    private final Map<String, OauthProvider> providers;

    public InMemoryProviderRepository(Map<String, OauthProvider> providers) {
        this.providers = new HashMap<>(providers);
    }

    public OauthProvider findByProviderName(String name) {
        return providers.get(name);
    }
}