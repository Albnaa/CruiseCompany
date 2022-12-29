package com.app.webapplication.model.entity;

public enum Role {
    ADMIN (1), USER (2);

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
