package com.cab.management.onboarding.dao;

import com.cab.management.onboarding.exceptions.ServiceNotLaunchedInCountryException;
import com.cab.management.onboarding.model.City;
import com.cab.management.onboarding.model.Country;

import java.util.HashMap;
import java.util.Map;

public class OnBoardingDao {

    private final Map<Integer, Country> onBoardedCountry = new HashMap<>();
    private final Map<String, City> onBoardedCity = new HashMap<>();

    public Country countryLaunch(final Country country) {
        return onBoardedCountry.put(country.getId(), country);
    }

    public boolean isServiceLaunched(final int countryId) {
        return onBoardedCountry.containsKey(countryId);
    }

    public City cityLaunch(final City city) {
        if (isServiceLaunched(city.getCountryId())) {
            return onBoardedCity.put(city.getPrimaryKey(), city);
        }
        throw new ServiceNotLaunchedInCountryException("Service is not yet available in the country " + city.getCountryId());
    }

    public boolean isServiceAvailable(final City city) {
        return onBoardedCity.containsKey(city.getPrimaryKey());
    }
}
