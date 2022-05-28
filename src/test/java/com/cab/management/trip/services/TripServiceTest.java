package com.cab.management.trip.services;

import com.cab.management.trip.dao.TripDao;
import com.cab.management.trip.model.Trip;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TripServiceTest {

    TripService tripService;

    @BeforeEach
    void beforeEach() {
        tripService = new TripService(new TripDao());
    }

    @Test
    void startTrip_success() {
        Trip trip = Trip.builder().build();
        String tripId = tripService.startTrip(trip);
        MatcherAssert.assertThat(tripId, Matchers.notNullValue());
    }

    @Test
    void endTrip_success() {
        Trip trip = Trip.builder().build();
        String tripId = tripService.startTrip(trip);
        MatcherAssert.assertThat(tripId, Matchers.notNullValue());

        Trip endTrip = tripService.endTrip(tripId);
        MatcherAssert.assertThat(tripId, Matchers.is(endTrip.getTripId()));
    }
}