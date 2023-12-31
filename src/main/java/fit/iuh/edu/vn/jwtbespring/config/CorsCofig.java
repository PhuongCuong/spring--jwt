package fit.iuh.edu.vn.jwtbespring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsCofig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000/") // Đặt origin của Frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true) // Cho phép sử dụng cookie
                .allowedHeaders("*");
    }


}
