package com.yutsuki.order_service.exception;

public class AuthException extends BaseException {
    public AuthException(String message) {
        super(message);
    }

    public static AuthException unauthorized() {
        return new AuthException("unauthorized");
    }


    public static AuthException credentialsInvalid() {
        return new AuthException("credentials.invalid");
    }
}
