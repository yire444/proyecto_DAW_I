package com.nova.talentnova.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private final String SECRET_KEY_STRING = "talentNovaSecretKeyJwtForSecurityModule2026SuperSecureKeyWithEnoughLength";
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());

    //TOKEN VENCE EN 24 HORAS
    private final long EXPIRATION_TIME = 86400000;

    //GENERAR TOKEN CON CORREO CORPORATIVO
    public String generateToken(String corporateEmail) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(corporateEmail)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    //OBTENER CORREO
    public String getCorporateEmailFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    //VALIDAR TOKEN Y TIEMPO DE EXPIRACIÓN
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // El token es inválido, expiró o está malformado
            return false;
        }
    }
}