package com.kaesar.saude.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegistroRequestDTO {

    @NotBlank
    private String login;

    @NotBlank
    private String senha;

    @NotNull
    private PerfilUsuario perfil;
}
