package com.investment.investment_platform.dto;

import java.util.List;

public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private boolean enabled;
    private List<String> roles;

    public UserResponseDTO(Long id, String username, String email, boolean enabled, List<String> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public List<String> getRoles() {
        return roles;
    }
}
