package com.cab.management.onboarding.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class City {
    private static final String DELIMETER = "#";

    private final int id;
    private final int countryId;
    private final String city;
    private final String state;

    public String getPrimaryKey() {
        return String.join(DELIMETER, Integer.toString(countryId), Integer.toString(id));
    }
}
