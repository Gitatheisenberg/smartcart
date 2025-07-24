package com.smartcart.user_service.entity;

public enum Role {
    USER,
    ADMIN;

    public static Role fromString(String role) {
        return Role.valueOf(role.trim().toUpperCase());
    }
}
