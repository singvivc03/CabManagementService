package com.cab.management.trip.dao;

import com.cab.management.trip.model.Trip;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TripDaoTest {

    TripDao tripDao;

    @BeforeEach
    void beforeEach() {
        tripDao = new TripDao();
    }

    @Test
    void startTrip_success() {
        Trip trip = Trip.builder().build();
        String tripId = tripDao.startTrip(trip);
        MatcherAssert.assertThat(tripId, Matchers.notNullValue());
    }

    @Test
    void endTrip_success() {
        Trip trip = Trip.builder().build();
        String tripId = tripDao.startTrip(trip);
        MatcherAssert.assertThat(tripId, Matchers.notNullValue());

        Trip endTrip = tripDao.endTrip(tripId);
        MatcherAssert.assertThat(tripId, Matchers.is(endTrip.getTripId()));
    }
}