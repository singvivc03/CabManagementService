package com.cab.management.onboarding.services;

import com.cab.management.onboarding.dao.OnBoardingDao;
import com.cab.management.onboarding.exceptions.ServiceAlreadyLaunchedException;
import com.cab.management.onboarding.model.City;

public class OnBoardingService {

    private final OnBoardingDao onBoardingDao;

    public OnBoardingService(final OnBoardingDao onBoardingDao) {
        this.onBoardingDao = onBoardingDao;
    }

    public void onBoardService(final City city) {
        if (onBoardingDao.cityLaunch(city) != null) {
            throw new ServiceAlreadyLaunchedException("service is already launched in the state " + city.getState());
        }
    }

    public boolean isServiceAvailableInState(final City city) {
        return onBoardingDao.isServiceAvailable(city);
    }
}
