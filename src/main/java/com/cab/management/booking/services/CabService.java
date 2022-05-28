package com.cab.management.booking.services;

import com.cab.management.booking.dao.BookingDao;
import com.cab.management.booking.model.Cab;

import java.util.List;

public class CabService {

    private final BookingDao bookingDao;

    public CabService(final BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    public List<Cab> getAllCabs() {
        return this.bookingDao.getAllCabs();
    }
}
