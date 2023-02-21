package com.java.cruisecompany.model.utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public final class PasswordHashUtil {
    private final static int ITERATIONS = 10;
    private final static int MEMORY = 1024 * 10;
    private final static int PARALLELISM = 1;
    private static final Argon2 argon2 = Argon2Factory.create();


    private PasswordHashUtil() {
    }

    public static String hash(String password) {
        return argon2.hash(ITERATIONS, MEMORY,PARALLELISM, password.toCharArray());
    }

    public static boolean verify(String hash, String password) {
        return argon2.verify(hash, password.toCharArray());
    }
}
