package com.java.cruisecompany.model.entity.enums;

/**
 * The Role enum represents the user role in the system.
 * The available roles are ADMIN and USER.
 * It provides methods to get the index value of the role and to retrieve a Role instance based on the index value or
 * the role name.
 */
public enum Role {

    ADMIN(1), USER(2);

    private final int index;

    /**
     * Constructs a new Role object with the specified index.
     *
     * @param index the index of the role.
     */
    Role(int index) {
        this.index = index;
    }

    /**
     * Returns the index value of the role.
     *
     * @return the index value of the role.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns the Role instance associated with the specified index value.
     *
     * @param index the index value of the role.
     * @return the Role instance associated with the specified index value.
     */
    public static Role getRole(int index) {
        if (index == 1) return ADMIN;
        return USER;
    }

    /**
     * Returns the Role instance associated with the specified role name.
     *
     * @param role the name of the role.
     * @return the Role instance associated with the specified role name.
     */
    public static Role parse(String role) {
        if (role.equals("ADMIN")) return ADMIN;
        return USER;
    }
}
