package org.dam.configuracion;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfiguracion implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5501") // Permitir todas las origenes
                .allowedMethods("*") // Permitir todos los métodos (GET, POST, PUT, DELETE, etc.)
                .allowedHeaders("*"); // Permitir todos los encabezados
    }
}







