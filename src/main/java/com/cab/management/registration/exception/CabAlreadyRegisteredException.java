package com.cab.management.registration.exception;

public class CabAlreadyRegisteredException extends RuntimeException {

    public CabAlreadyRegisteredException(final String message) {
        super(message);
    }
}
