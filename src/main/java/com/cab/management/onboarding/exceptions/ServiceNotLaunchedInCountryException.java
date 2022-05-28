package com.cab.management.onboarding.exceptions;

public class ServiceNotLaunchedInCountryException extends RuntimeException {

    public ServiceNotLaunchedInCountryException(final String message) {
        super(message);
    }
}
