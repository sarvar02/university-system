package uz.isystem.universitysystem._swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("uz.isystem.university"))
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "University System APIs",
                "Sample API for project",
                "1.0",
                "Not free to use",
                new springfox.documentation.service.Contact
                        ("Sarvar Tulkunov",
                                "https://github.com/sarvar02",
                                "sarvartulkunov2002@gmail.com"),
                "API License",
                "https://nuu.uz/",
                Collections.emptyList());
    }
}
