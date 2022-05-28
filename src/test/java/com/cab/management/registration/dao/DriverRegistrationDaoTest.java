package com.cab.management.registration.dao;

import com.cab.management.TestDataHelper;
import com.cab.management.registration.exception.CabAlreadyRegisteredException;
import com.cab.management.registration.model.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DriverRegistrationDaoTest {

    DriverRegistrationDao driverRegistrationDao;

    @BeforeEach
    void beforeEach() {
        driverRegistrationDao = new DriverRegistrationDao();
    }

    @Test
    void registerDriver_success() {
        Driver registeredDriver = driverRegistrationDao.registerDriver(TestDataHelper.getDefaultDriver().build());
        assertThat(registeredDriver.getId(), notNullValue());
    }

    @Test
    void registerDriver_throwsCabAlreadyRegisteredException() {
        Driver registeredDriver = driverRegistrationDao.registerDriver(TestDataHelper.getDefaultDriver().build());
        assertThat(registeredDriver.getId(), notNullValue());

        assertThrows(CabAlreadyRegisteredException.class,
            () -> driverRegistrationDao.registerDriver(registeredDriver));
    }

    @Test
    void getDriverByVehicleNumber_success() {
        Driver registeredDriver = driverRegistrationDao.registerDriver(TestDataHelper.getDefaultDriver().build());
        assertThat(registeredDriver.getId(), notNullValue());

        Driver foundDriver = driverRegistrationDao.getDriverByVehicleNumber(registeredDriver.getVehicle().getRegistrationNumber());
        assertThat(foundDriver.getId(), is(registeredDriver.getId()));
    }

    @Test
    void getDriverByVehicleNumber_returnNull_success() {
        Driver foundDriver = driverRegistrationDao.getDriverByVehicleNumber(TestDataHelper.VEHICLE_13.getRegistrationNumber());
        assertThat(foundDriver, nullValue());
    }
}