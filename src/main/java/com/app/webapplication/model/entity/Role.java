package com.app.webapplication.model.entity;

public enum Role {
    ADMIN (0), USER (1);

    private final int index;

    Role(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public Role getRole(int index) {
        if (index == 0) return ADMIN;
        return USER;
    }
}
