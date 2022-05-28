package com.cab.management.pricing.services;

import com.cab.management.pricing.model.Statement;
import com.cab.management.trip.model.Trip;

public class PricingService {

    public Statement generateStatement(final Trip trip) {
        return Statement.builder().build();
    }
}
