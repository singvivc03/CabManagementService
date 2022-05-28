package com.cab.management.registration.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Vehicle {

    private final String id;
    private final String model;
    private final String color;
    private final String registrationNumber;
}
