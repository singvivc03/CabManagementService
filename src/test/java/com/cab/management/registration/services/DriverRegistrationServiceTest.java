package com.cab.management.registration.services;

import com.cab.management.TestDataHelper;
import com.cab.management.registration.dao.DriverRegistrationDao;
import com.cab.management.registration.exception.CabAlreadyRegisteredException;
import com.cab.management.registration.model.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DriverRegistrationServiceTest {

    DriverRegistrationService driverRegistrationService;

    @BeforeEach
    void beforeEach() {
        driverRegistrationService = new DriverRegistrationService(new DriverRegistrationDao());
    }

    @Test
    void registerDriver_success() {
        Driver registeredDriver = driverRegistrationService.registerDriver(TestDataHelper.getDefaultDriver().build());
        assertThat(registeredDriver.getId(), notNullValue());
    }

    @Test
    void registerDriver_throwsCabAlreadyRegisteredException() {
        Driver registeredDriver = driverRegistrationService.registerDriver(TestDataHelper.getDefaultDriver().build());
        assertThat(registeredDriver.getId(), notNullValue());

        assertThrows(CabAlreadyRegisteredException.class,
            () -> driverRegistrationService.registerDriver(registeredDriver));
    }

    @Test
    void getDriverByVehicleNumber_success() {
        Driver registeredDriver = driverRegistrationService.registerDriver(TestDataHelper.getDefaultDriver().build());
        assertThat(registeredDriver.getId(), notNullValue());

        Driver foundDriver = driverRegistrationService.getDriverByVehicleNumber(registeredDriver.getVehicle().getRegistrationNumber());
        assertThat(foundDriver.getId(), is(registeredDriver.getId()));
    }

    @Test
    void getDriverByVehicleNumber_returnNull_success() {
        Driver foundDriver = driverRegistrationService.getDriverByVehicleNumber(TestDataHelper.VEHICLE_13.getRegistrationNumber());
        assertThat(foundDriver, nullValue());
    }
}