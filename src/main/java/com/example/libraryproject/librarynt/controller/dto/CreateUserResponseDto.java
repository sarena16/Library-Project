package com.example.libraryproject.librarynt.controller.dto;

import com.example.libraryproject.librarynt.commonTypes.UserRole;

public class CreateUserResponseDto {

    private long userId;
    private String username;
    private String role;

    public CreateUserResponseDto(long userId, String username, UserRole role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
