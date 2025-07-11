package com.lwd.gsc.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lwd
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("SpringBoot API 管理")
                        .contact(new Contact().name("lwd"))
                        .version("1.0")
                        .description( "Knife4j")
                        .license(new License().name("Apache 2.0")));
    }
}
