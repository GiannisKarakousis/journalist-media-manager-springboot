package com.example.journalist_media_manager_springboot.exception;

public class InvalidFieldValueException extends RuntimeException {

    public InvalidFieldValueException() {
        super();
    }

    public InvalidFieldValueException(String message) {
        super(message);
    }
}
