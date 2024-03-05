package store.mybooks.front.config;

import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import store.mybooks.front.utils.interceptor.CookieInterceptor;

/**
 * packageName    : store.mybooks.front.config
 * fileName       : WebClientConfig
 * author         : minsu11
 * date           : 2/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/19/24        minsu11       최초 생성
 */
@Configuration
public class WebClientConfig implements WebMvcConfigurer {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofSeconds(3L))
                .setReadTimeout(Duration.ofSeconds(3L))
                .build();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CookieInterceptor())
                .addPathPatterns("/user/**")
                .addPathPatterns("/admin/**")
                .addPathPatterns("/book/**")
                .addPathPatterns("/login","/");
    }
}