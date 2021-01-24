package br.com.image;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ImageApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ImageApplication.class, args);
    }

    @Bean
    public OpenAPI greetingApi() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("basicScheme",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("API para upload e download de imagens")
                .description("Esta API permite fazer upload, download, exclusão e listagem dos nomes das imagens criadas")
                .version("1.0.0");
    }

}
