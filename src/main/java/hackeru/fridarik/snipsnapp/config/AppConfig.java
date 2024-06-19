package hackeru.fridarik.snipsnapp.config;

import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {


    @Bean
    ModelMapper getModelMapper(){
        //CREATE A NEW INSTANCE OF MODEL MAPPER
        return new ModelMapper();
    }

    @Bean
    public WebMvcConfigurer cors() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings( CorsRegistry registry) {
                registry.addMapping("/**")
                .allowedOrigins(CorsConfiguration.ALL)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders(CorsConfiguration.ALL);
            }
        };
    }
}
