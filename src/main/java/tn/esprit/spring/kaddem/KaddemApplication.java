package tn.esprit.spring.kaddem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KaddemApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaddemApplication.class, args);
    }
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("tn.esprit.spring.kaddem"))  // Le package de ton application
                .paths(PathSelectors.any())  // Active toutes les routes
                .build();
    }


}
