package com.cab.management.trip.model;

import com.cab.management.booking.model.Cab;
import com.cab.management.booking.model.GeoLocation;
import com.cab.management.user.model.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class Trip {

    private final String tripId;
    private final User user;
    private final Cab cab;
    private final GeoLocation source;
    private final GeoLocation destination;
}
