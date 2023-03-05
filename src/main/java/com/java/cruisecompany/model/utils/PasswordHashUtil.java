package com.java.cruisecompany.model.utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/**
 * The PasswordHashUtil class provides static methods to hash and verify passwords using the Argon2 password hashing
 * algorithm.
 */
public final class PasswordHashUtil {
    private final static int ITERATIONS = 10;
    private final static int MEMORY = 1024 * 10;
    private final static int PARALLELISM = 1;
    private static final Argon2 argon2 = Argon2Factory.create();


    /**
     * Private constructor to prevent instantiation of the PasswordHashUtil class.
     */
    private PasswordHashUtil() {
    }

    /**
     * Hashes the specified password using the Argon2 password hashing algorithm with the default settings.
     *
     * @param password the password to hash
     * @return the resulting hash as a string
     */
    public static String hash(String password) {
        return argon2.hash(ITERATIONS, MEMORY, PARALLELISM, password.toCharArray());
    }

    /**
     * Verifies that the specified password matches the specified hash using the Argon2 password hashing algorithm.
     *
     * @param hash     the hash to verify against
     * @param password the password to verify
     * @return true if the password matches the hash, false otherwise
     */
    public static boolean verify(String hash, String password) {
        return argon2.verify(hash, password.toCharArray());
    }
}
