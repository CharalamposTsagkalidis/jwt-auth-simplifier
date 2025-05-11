package com.example.inter.JwtAuthFilter;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Value("${inter.app.jwtSecret}")
    private String jwtSecret;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        this.extractToken(request);
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    public boolean isTokenExpired(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration().before(new Date());
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(this.getSignInKey())
                .build()
                .parseSignedClaims(token).getPayload();
    }

    public String generateToken(Map<String, String> extraClaims, String username, long expireInterval) {
        return Jwts.builder().claims().add(extraClaims).and().subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expireInterval))
                .signWith(this.getSignInKey())
                .compact();

    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = jwtSecret.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
