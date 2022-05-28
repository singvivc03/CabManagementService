package com.cab.management.registration.dao;

import com.cab.management.registration.exception.CabAlreadyRegisteredException;
import com.cab.management.registration.model.Driver;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DriverRegistrationDao {

    private final Map<String, Driver> registeredDriver = new HashMap<>();
    private final Map<String, Driver> registeredCabByDriver = new HashMap<>();

    public Driver registerDriver(final Driver driver) {
        if (registeredCabByDriver.containsKey(driver.getVehicle().getRegistrationNumber())) {
            throw new CabAlreadyRegisteredException("Cab with this registration number is already registered");
        }
        Driver updatedDriver = driver.toBuilder().id(UUID.randomUUID().toString()).build();
        registeredDriver.put(updatedDriver.getId(), updatedDriver);
        registeredCabByDriver.put(updatedDriver.getVehicle().getRegistrationNumber(), updatedDriver);
        return updatedDriver;
    }

    public Driver getDriverByVehicleNumber(final String vehicleNumber) {
        return registeredCabByDriver.get(vehicleNumber);
    }
}
