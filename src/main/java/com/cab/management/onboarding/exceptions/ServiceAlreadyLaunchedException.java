package com.cab.management.onboarding.exceptions;

public class ServiceAlreadyLaunchedException extends RuntimeException {

    public ServiceAlreadyLaunchedException(final String message) {
        super(message);
    }
}
