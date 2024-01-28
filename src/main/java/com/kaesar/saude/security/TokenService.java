package com.kaesar.saude.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.kaesar.saude.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private static final String EMISSOR = "projeto-saude";

    @Value("security.token.secret.key")
    private String chave;

    public String geraToken(Usuario usuario) {
        String token = JWT.create()
                .withIssuer(EMISSOR)
                .withSubject(usuario.getUsername())
                .withExpiresAt(tempoDeExpiracao())
                .sign(Algorithm.HMAC256(chave));
        return token;
    }

    public String validaToken(String token) {
        return JWT.require(Algorithm.HMAC256(chave))
                .withIssuer(EMISSOR)
                .build()
                .verify(token)
                .getSubject();
    }

    private Instant tempoDeExpiracao(){
        return LocalDateTime.now()
                .plusHours(1)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
