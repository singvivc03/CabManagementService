package com.cab.management.booking.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BookingDetail {

    private final String bookingId;
    private final Cab cabDetail;
}
