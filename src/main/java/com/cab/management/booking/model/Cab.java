package com.cab.management.booking.model;

import com.cab.management.booking.enums.Status;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder(toBuilder = true)
@Getter
public class Cab {

    private final String id;
    private final String driverId;
    private final Status status;
    private final String cabRegistrationNumber;
    private final GeoLocation geoLocation;
    private final long pickupTime;
    private final long lastUpdateTime;

    public long waitTime() {
        long now = Instant.now().toEpochMilli();
        return now - lastUpdateTime;
    }
}
