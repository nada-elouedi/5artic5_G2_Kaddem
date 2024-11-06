package tn.esprit.spring.kaddem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springdoc.core.annotations.OpenAPIDefinition;
import org.springdoc.core.annotations.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Kaddem API", version = "v1", description = "Documentation de l'API Kaddem"))
public class KaddemApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaddemApplication.class, args);
    }
}
