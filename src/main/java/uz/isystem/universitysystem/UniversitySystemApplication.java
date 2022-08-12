package uz.isystem.universitysystem;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@SpringBootApplication
public class UniversitySystemApplication{

    public static void main(String[] args) {
        SpringApplication.run(UniversitySystemApplication.class, args);
    }

}
