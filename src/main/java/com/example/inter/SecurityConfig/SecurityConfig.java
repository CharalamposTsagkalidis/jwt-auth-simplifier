package com.example.inter.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.example.inter.JwtAuthFilter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    return    http.authorizeHttpRequests(authorizeHttp ->{
            authorizeHttp.requestMatchers("/api/login/secured").authenticated();
            authorizeHttp.anyRequest().permitAll();
        }).csrf(AbstractHttpConfigurer:: disable)
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
    
    }
}
