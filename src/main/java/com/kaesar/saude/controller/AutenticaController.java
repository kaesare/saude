package com.kaesar.saude.controller;

import com.kaesar.saude.domain.usuario.LoginRequestDTO;
import com.kaesar.saude.domain.usuario.LoginResponseDTO;
import com.kaesar.saude.domain.usuario.RegistroRequestDTO;
import com.kaesar.saude.domain.usuario.Usuario;
import com.kaesar.saude.repository.UsuarioRepository;
import com.kaesar.saude.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutenticaController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;

    @Operation(description = "Registra um beneficiário")
    @PostMapping(value = "/registra", consumes = "application/json")
    public ResponseEntity registra(@RequestBody @Valid final RegistroRequestDTO request){

           if(this.usuarioRepository.findByLogin(request.getLogin()) != null)
               return ResponseEntity.badRequest().build();

           String senha = new BCryptPasswordEncoder().encode(request.getSenha());
           Usuario usuario = new Usuario(request.getLogin(), senha, request.getPerfil());

           this.usuarioRepository.save(usuario);

           return ResponseEntity.ok().build();
    }

    @Operation(description = "Executa login")
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public LoginResponseDTO login(@RequestBody @Valid final LoginRequestDTO request){

        UsernamePasswordAuthenticationToken credencial = new UsernamePasswordAuthenticationToken(request.getLogin(), request.getSenha());

        Authentication autenticacao = this.authenticationManager.authenticate(credencial);

        String token = tokenService.geraToken((Usuario) autenticacao.getPrincipal());

        return new LoginResponseDTO(token);
    }
}