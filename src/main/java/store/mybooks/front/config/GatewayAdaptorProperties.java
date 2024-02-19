package store.mybooks.front.config;

import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * packageName    : store.mybooks.front.config
 * fileName       : GatewayAdaptorProperties
 * author         : minsu11
 * date           : 2/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/19/24        minsu11       최초 생성
 */
@Data
@ConfigurationProperties(prefix = "store.mybooks.front.config.gateway")
public class GatewayAdaptorProperties {
    @NotNull
    private String address;

}
