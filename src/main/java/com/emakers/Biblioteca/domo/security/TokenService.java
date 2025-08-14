package com.emakers.Biblioteca.domo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.emakers.Biblioteca.data.entity.Pessoa;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Pessoa pessoa){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("biblio")
                    .withSubject(pessoa.getEmail())
                    .withExpiresAt(expiration())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException jwtCreationException){
            throw new RuntimeException("Erro ao gerar token de verificação", jwtCreationException);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("biblio")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTCreationException jwtCreationException){
            return "";
        }
    }

    private Instant expiration(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
