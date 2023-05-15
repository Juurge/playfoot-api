package org.dam.aplicacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(scanBasePackages = {
		"org.dam.controlador"
})
public class PlayfootApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayfootApiApplication.class, args);
	}

}
