package com.workintech.s19_twitter_challange.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://localhost:3200")
                .allowedMethods("*") // GET, POST, DELETE, PUT vs.
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}