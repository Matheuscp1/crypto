package com.crypto.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openApi() {

        return new OpenAPI()
                .info(new Info()
                        .title("Crypto")
                        .version("v1")
                        .description("Crypto wallet management"))
                .tags(Arrays.asList(
                                new Tag().name("Wallet").description("Manage Wallet"),
                                new Tag().name("Asset").description("Manage Asset")

                        )
                )
                .components(new io.swagger.v3.oas.models.Components());

    }

}
