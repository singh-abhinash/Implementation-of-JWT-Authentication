package com.abhinash.implementJWT.springBootJWTAuthentication.exp;

/**
 * Created by Abhinash Singh - 2024
 */

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

