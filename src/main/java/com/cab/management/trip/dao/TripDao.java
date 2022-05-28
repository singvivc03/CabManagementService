package com.cab.management.trip.dao;

import com.cab.management.trip.model.Trip;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TripDao {

    private final Map<String, Trip> trips = new HashMap<>();

    public String startTrip(final Trip newTrip) {
        String tripId = UUID.randomUUID().toString();
        Trip generatedTrip = newTrip.toBuilder().tripId(tripId).build();
        trips.put(tripId, generatedTrip);
        return tripId;
    }

    public Trip getTripByTripId(final String tripId) {
        return trips.get(tripId);
    }

    public Trip endTrip(final String tripId) {
        return trips.remove(tripId);
    }
}
