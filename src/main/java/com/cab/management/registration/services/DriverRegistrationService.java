package com.cab.management.registration.services;

import com.cab.management.registration.dao.DriverRegistrationDao;
import com.cab.management.registration.model.Driver;

public class DriverRegistrationService {

    private final DriverRegistrationDao driverRegistrationDao;

    public DriverRegistrationService(final DriverRegistrationDao driverRegistrationDao) {
        this.driverRegistrationDao = driverRegistrationDao;
    }

    public Driver registerDriver(final Driver driver) {
        return driverRegistrationDao.registerDriver(driver);
    }

    public Driver getDriverByVehicleNumber(final String vehicleNumber) {
        return driverRegistrationDao.getDriverByVehicleNumber(vehicleNumber);
    }
}
