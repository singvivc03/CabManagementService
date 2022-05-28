package com.cab.management.booking.services;

import com.cab.management.booking.dao.BookingDao;
import com.cab.management.booking.model.BookingDetail;
import com.cab.management.booking.model.Cab;
import com.cab.management.pricing.model.Statement;
import com.cab.management.pricing.services.PricingService;
import com.cab.management.trip.model.Trip;
import com.cab.management.trip.services.TripService;

public class BookingService {

    private final BookingDao bookingDao;
    private final TripService tripService;
    private final PricingService pricingService;

    public BookingService(final BookingDao bookingDao, final TripService tripService,
                          final PricingService pricingService) {
        this.bookingDao = bookingDao;
        this.tripService = tripService;
        this.pricingService = pricingService;
    }

    public BookingDetail bookCab(final Trip trip) {
        Cab allocatingCab = bookingDao.bookCab(trip.getSource());
        Trip newTrip = trip.toBuilder().cab(allocatingCab).build();
        String tripId = tripService.startTrip(newTrip);
        return BookingDetail.builder().bookingId(tripId).cabDetail(allocatingCab).build();
    }

    public Statement releaseCab(final String bookingId) {
        Trip runningTrip = tripService.endTrip(bookingId);
        bookingDao.releaseCab(runningTrip.getCab().getId());
        return pricingService.generateStatement(runningTrip);
    }
}
