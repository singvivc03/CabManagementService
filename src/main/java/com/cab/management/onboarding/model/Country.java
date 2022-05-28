package com.cab.management.onboarding.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Country {

    private final int id;
    private final String countryName;
    private final String countryCode;
}
