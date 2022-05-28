package com.cab.management.booking.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GeoLocation {

    private static final String DELIMETER = "#";

    private final String latitude;
    private final String longitude;
    private final int cityId;
    private final int countryId;

    public String getPrimaryKey() {
        return String.join(DELIMETER, Integer.toString(countryId), Integer.toString(cityId));
    }
}
