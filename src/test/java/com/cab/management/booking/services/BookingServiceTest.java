package com.cab.management.booking.services;

import com.cab.management.TestDataHelper;
import com.cab.management.booking.dao.BookingDao;
import com.cab.management.booking.exceptions.CabNotAvailableException;
import com.cab.management.booking.model.BookingDetail;
import com.cab.management.booking.model.Cab;
import com.cab.management.booking.model.GeoLocation;
import com.cab.management.pricing.model.Statement;
import com.cab.management.pricing.services.PricingService;
import com.cab.management.trip.model.Trip;
import com.cab.management.trip.services.TripService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Map;
import java.util.PriorityQueue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private TripService tripService;

    @Mock
    private PricingService pricingService;

    BookingService bookingService;

    @BeforeEach
    void beforeEach() {
        Map<String, PriorityQueue<Cab>> cityWiseAvailableCab = TestDataHelper.createPoolOfCabs();
        BookingDao bookingDao = new BookingDao(cityWiseAvailableCab);
        bookingService = new BookingService(bookingDao, tripService, pricingService);
    }

    @Test
    void bookCab_success() {
        when(tripService.startTrip(any(Trip.class))).thenReturn("1");
        Trip trip = Trip.builder()
            .source(GeoLocation.builder().cityId(1).countryId(1).build())
            .destination(GeoLocation.builder().cityId(2).countryId(1).build()).build();
        BookingDetail bookingDetail = bookingService.bookCab(trip);
        assertThat(bookingDetail.getBookingId(), is("1"));
        assertThat(bookingDetail.getCabDetail(), notNullValue());
    }

    @Test
    void bookCab_throwsCabNotAvailableException() {
        Trip trip = Trip.builder()
            .source(GeoLocation.builder().cityId(5).countryId(1).build())
            .destination(GeoLocation.builder().cityId(2).countryId(1).build()).build();
        Assertions.assertThrows(CabNotAvailableException.class, () -> bookingService.bookCab(trip));
        Mockito.verifyZeroInteractions(tripService);
    }

    @Test
    void releaseCab_success() {
        when(tripService.startTrip(any(Trip.class))).thenReturn("1");
        Trip trip = Trip.builder()
            .source(GeoLocation.builder().cityId(1).countryId(1).build())
            .destination(GeoLocation.builder().cityId(2).countryId(1).build()).build();
        BookingDetail bookingDetail = bookingService.bookCab(trip);
        trip = trip.toBuilder().cab(bookingDetail.getCabDetail()).build();
        when(tripService.endTrip(bookingDetail.getBookingId())).thenReturn(trip);
        when(pricingService.generateStatement(any(Trip.class))).thenReturn(Statement.builder()
            .cab(trip.getCab()).price(new BigDecimal(10)).build());
        Statement statement = bookingService.releaseCab(bookingDetail.getBookingId());
        assertThat(statement.getPrice(), is(new BigDecimal(10)));
    }
}