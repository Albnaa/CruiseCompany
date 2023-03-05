package com.java.cruisecompany.model.entity.enums;

/**
 * The Status enumeration represents the possible statuses of a booking.
 * The available statuses are: UNCHECKED, UNPAID, PAID, and COMPLETED.
 * Each status is associated with a unique integer index.
 */
public enum Status {
    UNCHECKED(1), UNPAID(2), PAID(3), COMPLETED(4);

    private final int index;

    /**
     * Constructs a new Status with the given index.
     *
     * @param index the integer index associated with this status
     */
    Status(int index) {
        this.index = index;
    }

    /**
     * Returns the integer index associated with this status.
     *
     * @return the integer index of this status
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns the Status corresponding to the specified integer index.
     *
     * @param index the integer index of the desired Status
     * @return the Status corresponding to the specified index
     */
    public static Status getStatus(int index) {
        Status status;
        switch (index) {
            case 1 -> status = UNCHECKED;
            case 2 -> status = UNPAID;
            case 3 -> status = PAID;
            default -> status = COMPLETED;
        }
        return status;
    }

    /**
     * Returns the Status corresponding to the specified string.
     *
     * @param string the string representation of the desired Status
     * @return the Status corresponding to the specified string
     */
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
