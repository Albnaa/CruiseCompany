package com.java.cruisecompany.model.entity.enums;

public enum Role {
    ADMIN (1), USER (2);

    private final int index;

    Role(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static Role getRole(int index) {
        if (index == 1) return ADMIN;
        return USER;
    }

    public static Role getRoleFromString(String role) {
        if (role.equals("ADMIN")) return ADMIN;
        return USER;
    }
}
