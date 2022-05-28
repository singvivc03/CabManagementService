package com.cab.management.trip.services;

import com.cab.management.trip.dao.TripDao;
import com.cab.management.trip.model.Trip;

public class TripService {

    private final TripDao tripDao;

    public TripService(final TripDao tripDao) {
        this.tripDao = tripDao;
    }

    public String startTrip(final Trip trip) {
        return tripDao.startTrip(trip);
    }

    public Trip endTrip(final String tripId) {
        return tripDao.endTrip(tripId);
    }
}
