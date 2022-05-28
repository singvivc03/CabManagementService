package com.cab.management.onboarding.services;

import com.cab.management.onboarding.dao.OnBoardingDao;
import com.cab.management.onboarding.exceptions.ServiceAlreadyLaunchedException;
import com.cab.management.onboarding.exceptions.ServiceNotLaunchedInCountryException;
import com.cab.management.onboarding.model.City;
import com.cab.management.onboarding.model.Country;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OnBoardingServiceTest {

    OnBoardingService onBoardingService;

    @BeforeEach
    void beforeEach() {
        OnBoardingDao onBoardingDao = new OnBoardingDao();
        onBoardingDao.countryLaunch(Country.builder().id(1).countryName("India").countryCode("IN").build());
        onBoardingService = new OnBoardingService(onBoardingDao);
    }

    @Test
    void onBoardService_success() {
        City city = City.builder().id(1).countryId(1).state("Pune").build();
        onBoardingService.onBoardService(city);
        MatcherAssert.assertThat(onBoardingService.isServiceAvailableInState(city), Matchers.is(true));
    }

    @Test
    void onBoardService_throwsServiceAlreadyLaunchedException() {
        City city = City.builder().id(1).countryId(1).state("Pune").build();
        onBoardingService.onBoardService(city);
        MatcherAssert.assertThat(onBoardingService.isServiceAvailableInState(city), Matchers.is(true));

        Assertions.assertThrows(ServiceAlreadyLaunchedException.class, () -> onBoardingService.onBoardService(city));
    }

    @Test
    void onBoardService_throwsServiceNotLaunchedInCountryException() {
        City city = City.builder().id(1).countryId(2).state("Pune").build();
        Assertions.assertThrows(ServiceNotLaunchedInCountryException.class, () -> onBoardingService.onBoardService(city));
    }
}