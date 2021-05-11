package dfob.data.springsecurity_ii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class SpringSecurityIiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityIiApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean()
    {
        FilterRegistrationBean filterRegistrationBean1 = new FilterRegistrationBean();
        filterRegistrationBean1.setFilter(new JwtFilter());
        // All the request that try to go thru /api/hello has to pass thru the mechanism of the filter.
        filterRegistrationBean1.setUrlPatterns(Collections.singleton("/api/hello/*"));
        return filterRegistrationBean1;
    }
}
