package com.projetoTeorico.crudLivros.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registro) {
        registro.addMapping("/**")
                .allowedOrigins("http://localhost:5173/")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
