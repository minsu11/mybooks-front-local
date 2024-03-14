package store.mybooks.front.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * packageName    : store.mybooks.front.config<br>
 * fileName       : TossAppKey<br>
 * author         : minsu11<br>
 * date           : 3/13/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/13/24        minsu11       최초 생성<br>
 */
@Getter
@ConfigurationProperties(prefix = "toss.api")
@RequiredArgsConstructor
public class TossAppKey {
    private String key;
    private String clientKey;
    private final KeyConfig keyConfig;


    public void setKey(String key) {
        this.key = keyConfig.keyStore(key);
    }

    public void setClientKey(String key) {
        this.clientKey = keyConfig.keyStore(key);
    }
}
