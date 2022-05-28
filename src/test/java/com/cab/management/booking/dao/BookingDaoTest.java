package com.cab.management.booking.dao;

import com.cab.management.TestDataHelper;
import com.cab.management.booking.enums.Status;
import com.cab.management.booking.exceptions.CabNotAvailableException;
import com.cab.management.booking.model.Cab;
import com.cab.management.booking.model.GeoLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import static com.cab.management.TestDataHelper.CITY_1;
import static com.cab.management.TestDataHelper.DRIVER_13;
import static com.cab.management.TestDataHelper.createDefaultCab;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookingDaoTest {

    BookingDao bookingDao;

    @BeforeEach
    void beforeEach() {
        Map<String, PriorityQueue<Cab>> cityWiseAvailableCab = TestDataHelper.createPoolOfCabs();
        this.bookingDao = new BookingDao(cityWiseAvailableCab);
    }

    @Test
    void bookCab_success() {
        GeoLocation userLocation = GeoLocation.builder().countryId(1).cityId(1).build();
        Cab bookedCab = bookingDao.bookCab(userLocation);
        assertThat(bookedCab, notNullValue());
        assertThat(bookedCab.getStatus(), is(Status.ON_TRIP));

        Map<String, List<Cab>> cabsOnTrip = bookingDao.getCabsOnTrip();
        assertThat(cabsOnTrip.values(), hasSize(1));
    }

    @Test
    void bookCab_throwsCabNotAvailableException() {
        GeoLocation userLocation = GeoLocation.builder().countryId(1).cityId(5).build();
        assertThrows(CabNotAvailableException.class, () -> bookingDao.bookCab(userLocation));
    }

    @Test
    void releaseCab_success() {
        GeoLocation userLocation = GeoLocation.builder().countryId(1).cityId(1).build();
        Cab bookedCab = bookingDao.bookCab(userLocation);

        Map<String, List<Cab>> cabsOnTrip = bookingDao.getCabsOnTrip();
        assertThat(cabsOnTrip.values(), hasSize(1));

        bookingDao.releaseCab(bookedCab.getId());
        cabsOnTrip = bookingDao.getCabsOnTrip();
        assertThat(cabsOnTrip.values(), hasSize(0));
    }

    @Test
    void bookCab_shouldPickUpCabWithMaxIdleTime() {
        BookingDao bookingDao = setupBookingDao();
        GeoLocation userLocation = GeoLocation.builder().countryId(1).cityId(1).build();
        Cab bookedCab = bookingDao.bookCab(userLocation);

        assertThat(bookedCab.getStatus(), is(Status.ON_TRIP));
        assertThat(bookedCab.getDriverId(), is(DRIVER_13.getId()));
    }

    private BookingDao setupBookingDao() {
        Map<String, PriorityQueue<Cab>> cityWiseAvailableCab = TestDataHelper.createPoolOfCabs();
        Instant pickupTime = Instant.now().minus(1, ChronoUnit.DAYS);
        Cab cab = createDefaultCab(CITY_1, "18.453713084430054",
            "73.96870745323889", DRIVER_13, 0).pickupTime(pickupTime.toEpochMilli())
            .lastUpdateTime(pickupTime.plus(1, ChronoUnit.HOURS).toEpochMilli()).build();
        var pool = cityWiseAvailableCab.get(cab.getGeoLocation().getPrimaryKey());
        pool.offer(cab);
        cityWiseAvailableCab.put(cab.getGeoLocation().getPrimaryKey(), pool);
        return new BookingDao(cityWiseAvailableCab);
    }
}