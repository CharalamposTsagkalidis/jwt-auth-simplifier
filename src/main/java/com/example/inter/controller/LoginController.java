package com.example.inter.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inter.service.ApiTokenService;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    private final ApiTokenService apiTokenService;

    public LoginController(ApiTokenService apiTokenService) {
        this.apiTokenService = apiTokenService;
    }

    @PostMapping("/secured")
    public String login() {
        return "This is a secured endpoint!";
    }

    @PostMapping("/unsecured")   
    public String loginUnsecured() {
        return "This is an unsecured endpoint!";
    }

    @PostMapping("/unsecured/login")
    public String getJwt(@RequestHeader ("HeaderAdmin") String username) {
        return this.apiTokenService.generateJwt(username);
    }
}
