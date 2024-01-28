package com.kaesar.saude;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Sistema de Saúde", version = "1.0", description = "Cadastro de beneficiários e seus documentos"))
public class SaudeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaudeApplication.class, args);
	}

}
