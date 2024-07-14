package com.hamysecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The CorsConfig class configures CORS (Cross-Origin Resource Sharing) settings for the application.
 * It allows the application to handle requests from different origins.
 */
@Configuration
public class CorsConfig {

    /**
     * Configures the CORS settings.
     * 
     * @return a WebMvcConfigurer that defines the CORS configuration.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * Adds CORS mappings to the registry.
             * 
             * @param registry the CorsRegistry to configure.
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        // Allow requests from this origin (adjust as needed for your front-end URL)
                        .allowedOrigins("http://localhost:3000")
                        // Allow these HTTP methods
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        // Allow all headers
                        .allowedHeaders("*")
                        // Allow credentials (e.g., cookies, authorization headers)
                        .allowCredentials(true);
            }
        };
    }
}
