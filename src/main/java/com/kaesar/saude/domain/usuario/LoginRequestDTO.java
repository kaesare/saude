package com.kaesar.saude.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @NotBlank
    private String login;

    @NotBlank
    private String senha;
}
