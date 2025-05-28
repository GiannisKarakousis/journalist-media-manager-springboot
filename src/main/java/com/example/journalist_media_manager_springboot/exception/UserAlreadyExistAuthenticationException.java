package com.example.journalist_media_manager_springboot.exception;

public class UserAlreadyExistAuthenticationException extends ArithmeticException {

    public UserAlreadyExistAuthenticationException(final String msg) {
        super(msg);
    }
}
