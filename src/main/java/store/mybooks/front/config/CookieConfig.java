package store.mybooks.front.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : store.mybooks.front.config<br>
 * fileName       : CookieConfig<br>
 * author         : masiljangajji<br>
 * date           : 3/9/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/9/24        masiljangajji       최초 생성
 */

@Getter
@Configuration
public class CookieConfig {

    @Value("${cookie.domain}")
    private String domain;

}
