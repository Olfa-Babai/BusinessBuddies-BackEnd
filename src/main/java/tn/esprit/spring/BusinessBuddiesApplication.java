package tn.esprit.spring;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication

public class BusinessBuddiesApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BusinessBuddiesApplication.class, args);
		//Sytem.out.println(LocalDate ld=LocalDate.now());
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200");
            }
        };
    }


}
