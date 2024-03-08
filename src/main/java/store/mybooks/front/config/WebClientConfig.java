package store.mybooks.front.config;

import java.time.Duration;
import java.util.Collections;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import store.mybooks.front.auth.interceptor.CookieInterceptor;
import store.mybooks.front.auth.interceptor.LogoutInterceptor;

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
                .addPathPatterns("/cart/**")
                .addPathPatterns("/", "/login", "/signup")
                .excludePathPatterns("/**/*.js")
                .excludePathPatterns("/**/*.scss")
                .excludePathPatterns("/**/*.css")
                .excludePathPatterns("/**/*.json")
                .excludePathPatterns("/**/*.svg")
                .excludePathPatterns("/**/*.jpg")
                .excludePathPatterns("/**/*.png")
                .excludePathPatterns("/**/*.woff2");

        registry.addInterceptor(new LogoutInterceptor())
                .addPathPatterns("/logout")
                .addPathPatterns("/user/delete")
                .addPathPatterns("/user/modify/password");
    }

    @Bean
    public ServletContextInitializer clearJsession() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
                SessionCookieConfig sessionCookieConfig=servletContext.getSessionCookieConfig();
                sessionCookieConfig.setHttpOnly(true);
            }
        };
    }

}