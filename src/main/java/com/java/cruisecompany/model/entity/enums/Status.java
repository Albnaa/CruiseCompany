package com.java.cruisecompany.model.entity.enums;

public enum Status {
    UNCHECKED(1), UNPAID(2), PAID(3), COMPLETED(4);

    private final int index;

    Status(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static Status getStatus (int index) {
        Status status;
        switch (index) {
            case 1 -> status = UNCHECKED;
            case 2 -> status = UNPAID;
            case 3 -> status = PAID;
            default -> status = COMPLETED;
        }
        return status;
    }

    public static Status parse(String string) {
        Status status;
        switch (string) {
            case "UNCHECKED" -> status = UNCHECKED;
            case "UNPAID" -> status = UNPAID;
            case "PAID" -> status = PAID;
            default -> status = COMPLETED;
        }
        return status;
    }
}
