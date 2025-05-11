package com.example.inter.entity;

import java.util.HashSet;
import java.util.Set;

public class Users {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Set<Roles> roles = new HashSet();

    public Users() {
    }

    public Users(String username, String password, String email, Long id, Set<Roles> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = id;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public void addRole(Roles role) {
        this.roles.add(role);
    }

    public void removeRole(Roles role) {
        this.roles.remove(role);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
