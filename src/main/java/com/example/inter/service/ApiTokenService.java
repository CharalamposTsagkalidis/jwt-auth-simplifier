package com.example.inter.service;

import org.springframework.stereotype.Component;

import com.example.inter.entity.Roles;
import com.example.inter.entity.Users;
import com.example.inter.helper.GenerateJwtException;
import com.example.inter.helper.UserGenerationException;

@Component
public class ApiTokenService {
    private final TokenHelperService tokenHelperService;

    public ApiTokenService(TokenHelperService tokenHelperService) {
        this.tokenHelperService = tokenHelperService;
    }

    public Users generateUserDetails() {
        Users user = new Users();
        user.setId(1L);
        user.setUsername("user");
        user.setPassword("password");
        user.setEmail("mail@mail.com");
        user.addRole(new Roles(1L, "ROLE_USER"));
        return user;
    }

    public String generateJwt(String username) {
        try {
            Users users = this.generateUserDetails();
            String jwt = this.tokenHelperService.generateToken(users, username, 1000 * 60 * 60 * 10);
            return jwt;
        } catch (GenerateJwtException | UserGenerationException e) {
            throw new RuntimeException("Error generating JWT");
        }
    }
}
