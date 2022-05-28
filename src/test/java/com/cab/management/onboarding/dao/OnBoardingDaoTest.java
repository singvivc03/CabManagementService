package com.cab.management.onboarding.dao;

import com.cab.management.onboarding.exceptions.ServiceNotLaunchedInCountryException;
import com.cab.management.onboarding.model.City;
import com.cab.management.onboarding.model.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OnBoardingDaoTest {

    OnBoardingDao onBoardingDao;

    @BeforeEach
    void beforeEach() {
        onBoardingDao = new OnBoardingDao();
        Country country = Country.builder().countryCode("IN").countryName("India").id(1).build();
        onBoardingDao.countryLaunch(country);
    }

    @Test
    void countryLaunch_success() {
        onBoardingDao = new OnBoardingDao();
        Country country = Country.builder().countryCode("IN").countryName("India").id(1).build();
        onBoardingDao.countryLaunch(country);
        assertThat(onBoardingDao.isServiceLaunched(country.getId()), is(true));
    }

    @Test
    void cityLaunch_success() {
        City city = City.builder().id(1).countryId(1).state("Pune").build();
        onBoardingDao.cityLaunch(city);
        assertThat(onBoardingDao.isServiceAvailable(city), is(true));
    }

    @Test
    void cityLaunch_throwsServiceNotLaunchedInCountryException() {
        City city = City.builder().id(1).countryId(2).state("Pune").build();
        assertThrows(ServiceNotLaunchedInCountryException.class, () -> onBoardingDao.cityLaunch(city));
    }
}