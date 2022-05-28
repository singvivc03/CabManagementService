package com.cab.management.registration.model;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class Driver {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final int countryId;
    private final int cityId;
    private final String license;
    private final Vehicle vehicle;
}
