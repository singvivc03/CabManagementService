package com.cab.management.pricing.model;

import com.cab.management.booking.model.Cab;
import com.cab.management.booking.model.GeoLocation;
import com.cab.management.user.model.User;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class Statement {

    private final GeoLocation source;
    private final GeoLocation destination;
    private final User user;
    private final Cab cab;
    private final BigDecimal price;
    private final BigDecimal taxes;
    private final BigDecimal totalAmount;
}
