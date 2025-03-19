package com.fatec.projeto.projeto2025.config;

import javax.management.openmbean.OpenMBeanParameterInfo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
            .info(new Info()
                        .title(title:"API de projeto 2025 - Semestre 1")
                        .version(version:"1.0")
                        .description(description"Documentacao da API do projeto 2025 - semestre 1" ))
        };
    }

}
