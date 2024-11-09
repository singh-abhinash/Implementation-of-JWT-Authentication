package com.abhinash.implementJWT.springBootJWTAuthentication.exp;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

