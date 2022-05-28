package com.cab.management.booking.exceptions;

public class CabNotAvailableException extends RuntimeException {

    public CabNotAvailableException(final String message) {
        super(message);
    }
}
