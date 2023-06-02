package org.dam.configuracion;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfiguracion implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Permitir todas las origenes
                .allowedMethods("*") // Permitir todos los métodos (GET, POST, PUT, DELETE, etc.)
                .allowedHeaders("*"); // Permitir todos los encabezados
    }
}







