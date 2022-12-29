package com.app.webapplication.model.entity;

public enum Status {
    PAID(1), UNPAID(2);

    private final int index;

    Status(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public Status getStatus (int index) {
        if (index == 0) return PAID;
        return UNPAID;
    }
}
