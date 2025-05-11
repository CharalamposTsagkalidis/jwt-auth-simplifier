package com.example.inter.service;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.example.inter.entity.Users;
import com.example.inter.helper.UserGenerationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenHelperService {

    public boolean isTokenExpired(String token) throws Exception {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration().before(new Date());
    }

    public String extractUsername(String token) throws Exception {
        return extractAllClaims(token).getSubject();
    }

    public Claims extractAllClaims(String token) throws Exception {
        return Jwts.parser()
                .verifyWith(this.getSignInKey())
                .build()
                .parseSignedClaims(token).getPayload();
    }

    public String generateToken(Users user, String username, long expireInterval) throws UserGenerationException {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userDetails", user);

        return Jwts.builder().claims().add(claims).and().subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expireInterval))
                .signWith(this.getSignInKey())
                .compact();

    }

    private String generateKeySecretKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey originalKey = keyGen.generateKey();
            return Base64.getEncoder().encodeToString(originalKey.getEncoded());
        } catch (Exception e) {
            throw new UserGenerationException("Error generating secret key");
        }
    }

    private SecretKey getSignInKey() {
        try {
            String secretKey = this.generateKeySecretKey();
            byte[] keyBytes = Base64.getDecoder().decode(secretKey);
            return Keys.hmacShaKeyFor(keyBytes);
        } catch (Exception e) {
            throw new UserGenerationException("Error generating secret key");
        }
    }
}