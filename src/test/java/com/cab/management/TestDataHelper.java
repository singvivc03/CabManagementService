package com.cab.management;

import com.cab.management.booking.enums.Status;
import com.cab.management.booking.model.Cab;
import com.cab.management.booking.model.Cab.CabBuilder;
import com.cab.management.booking.model.GeoLocation;
import com.cab.management.onboarding.model.City;
import com.cab.management.registration.model.Driver;
import com.cab.management.registration.model.Driver.DriverBuilder;
import com.cab.management.registration.model.Vehicle;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.UUID;

public final class TestDataHelper {

    private static final Vehicle VEHICLE_1 = createDefaultVehicle("Maruti Suzuki", "White", "MH02PN1234");
    private static final Vehicle VEHICLE_2 = createDefaultVehicle("Hundai i10", "Black", "MH03PN1324");
    private static final Vehicle VEHICLE_3 = createDefaultVehicle("Hundai i20", "Silver", "MH04PN2324");
    private static final Vehicle VEHICLE_4 = createDefaultVehicle("Mercedes", "White", "MH05PN2314");
    private static final Vehicle VEHICLE_5 = createDefaultVehicle("Maruti Suzuki", "White", "DL02PN1234");
    private static final Vehicle VEHICLE_6 = createDefaultVehicle("Hundai i10", "Black", "DL03PN1324");
    private static final Vehicle VEHICLE_7 = createDefaultVehicle("Hundai i20", "Silver", "DL04PN2324");
    private static final Vehicle VEHICLE_8 = createDefaultVehicle("Mercedes", "White", "DL05PN2314");
    private static final Vehicle VEHICLE_9 = createDefaultVehicle("Maruti Suzuki", "White", "KA02PN1234");
    private static final Vehicle VEHICLE_10 = createDefaultVehicle("Hundai i10", "Black", "KA03PN1324");
    private static final Vehicle VEHICLE_11 = createDefaultVehicle("Hundai i20", "Silver", "KA04PN2324");
    private static final Vehicle VEHICLE_12 = createDefaultVehicle("Mercedes", "White", "KA05PN2314");

    public static final Vehicle VEHICLE_13 = createDefaultVehicle("Mercedes", "White", "MH05PN5000");

    public static final City CITY_1 = City.builder().countryId(1).id(1).city("Pune").state("Maharashtra").build();
    public static final City CITY_2 = City.builder().countryId(1).id(2).city("Delhi").state("New Delhi").build();
    public static final City CITY_3 = City.builder().countryId(1).id(3).city("Bangalore").state("Karnataka").build();

    public static final Driver DRIVER_1 = createDefaultDriver("Some", "one", "L1371910NP", VEHICLE_1, CITY_1);
    public static final Driver DRIVER_2 = createDefaultDriver("Some", "two", "L2372920NQ", VEHICLE_2, CITY_1);
    public static final Driver DRIVER_3 = createDefaultDriver("Some", "three", "L3373930NR", VEHICLE_3, CITY_1);
    public static final Driver DRIVER_4 = createDefaultDriver("Some", "four", "L4374940NS", VEHICLE_4, CITY_1);
    public static final Driver DRIVER_5 = createDefaultDriver("Some", "five", "M1371910NP", VEHICLE_5, CITY_2);
    public static final Driver DRIVER_6 = createDefaultDriver("Some", "six", "M2372920NQ", VEHICLE_6, CITY_2);
    public static final Driver DRIVER_7 = createDefaultDriver("Some", "seven", "M3373930NR", VEHICLE_7, CITY_2);
    public static final Driver DRIVER_8 = createDefaultDriver("Some", "eight", "M4374940NS", VEHICLE_8, CITY_2);
    public static final Driver DRIVER_9 = createDefaultDriver("Some", "nine", "K1371910NP", VEHICLE_9, CITY_3);
    public static final Driver DRIVER_10 = createDefaultDriver("Some", "ten", "K2372920NQ", VEHICLE_10, CITY_3);
    public static final Driver DRIVER_11 = createDefaultDriver("Some", "eleven", "K3373930NR", VEHICLE_11, CITY_3);
    public static final Driver DRIVER_12 = createDefaultDriver("Some", "twelve", "K4374940NS", VEHICLE_12, CITY_3);

    public static final Driver DRIVER_13 = createDefaultDriver("Some", "thirteen", "L4374940PS", VEHICLE_13, CITY_1);

    public static final CabBuilder CAB_1 = createDefaultCab(CITY_1, "18.553713084430054", "73.76870745323889", DRIVER_1, 0);
    public static final CabBuilder CAB_2 = createDefaultCab(CITY_1, "18.553713033430054", "73.76871745323889", DRIVER_2, 10);
    public static final CabBuilder CAB_3 = createDefaultCab(CITY_1, "18.553713904430054", "73.76872745323889", DRIVER_3, 20);
    public static final CabBuilder CAB_4 = createDefaultCab(CITY_1, "18.553713012430054", "73.76873745323889", DRIVER_4, 30);
    public static final CabBuilder CAB_5 = createDefaultCab(CITY_2, "28.553713082230054", "83.96870745323889", DRIVER_5, 0);
    public static final CabBuilder CAB_6 = createDefaultCab(CITY_2, "28.553713987430054", "83.96871745323889", DRIVER_6, 10);
    public static final CabBuilder CAB_7 = createDefaultCab(CITY_2, "28.523413084430054", "83.96872745323889", DRIVER_7, 20);
    public static final CabBuilder CAB_8 = createDefaultCab(CITY_2, "28.555463084430054", "83.96873745323889", DRIVER_8, 30);
    public static final CabBuilder CAB_9 = createDefaultCab(CITY_3, "38.557983084430054", "63.56870745323889", DRIVER_9, 0);
    public static final CabBuilder CAB_10 = createDefaultCab(CITY_3, "38.518793084430054", "63.56871745323889", DRIVER_10, 10);
    public static final CabBuilder CAB_11 = createDefaultCab(CITY_3, "38.550808084430054", "63.56872745323889", DRIVER_11, 20);
    public static final CabBuilder CAB_12 = createDefaultCab(CITY_3, "38.551113084430054", "63.56873745323889", DRIVER_12, 30);

    private TestDataHelper() {}

    private static Vehicle createDefaultVehicle(final String model, final String color, final String registrationNumber) {
        return Vehicle.builder().id(UUID.randomUUID().toString()).model(model)
            .color(color).registrationNumber(registrationNumber).build();
    }

    private static Driver createDefaultDriver(final String firstName, final String lastName, final String licence,
                                              final Vehicle vehicle, final City city) {
        String id = UUID.randomUUID().toString();
        return Driver.builder().id(id).cityId(city.getId()).countryId(city.getCountryId()).firstName(firstName)
            .lastName(lastName)
            .license(licence).vehicle(vehicle).build();
    }

    public static DriverBuilder getDefaultDriver() {
        return Driver.builder().cityId(CITY_1.getId()).countryId(CITY_1.getCountryId()).firstName("First Name")
            .lastName("Last Name")
            .license("01801NKQE").vehicle(VEHICLE_1);
    }

    public static CabBuilder createDefaultCab(final City city, final String latitude,
                                              final String longitude, final Driver driver, final int from) {
        Instant instant = Instant.now().minus(from, ChronoUnit.HOURS);
        return Cab.builder().id(UUID.randomUUID().toString())
            .cabRegistrationNumber(driver.getVehicle().getRegistrationNumber())
            .status(Status.IDLE).driverId(UUID.randomUUID().toString())
            .geoLocation(GeoLocation.builder()
                .latitude(latitude)
                .longitude(longitude)
                .cityId(city.getId()).countryId(city.getCountryId()).build())
            .pickupTime(instant.toEpochMilli()).lastUpdateTime(instant.plus(from, ChronoUnit.HOURS).toEpochMilli())
            .driverId(driver.getId());
    }

    public static Map<String, PriorityQueue<Cab>> createPoolOfCabs() {
        List<Cab> cabs = List.of(CAB_1.build(), CAB_2.build(), CAB_3.build(), CAB_4.build(), CAB_5.build(),
            CAB_6.build(), CAB_7.build(), CAB_8.build(), CAB_9.build(), CAB_10.build(), CAB_11.build(), CAB_12.build());
        Map<String, PriorityQueue<Cab>> poolOfCabsByCity = new HashMap<>();
        for (Cab cab : cabs) {
            var pool = poolOfCabsByCity.getOrDefault(cab.getGeoLocation().getPrimaryKey(),
                new PriorityQueue<>((cab1, cab2) -> (int) (cab2.waitTime() - cab1.waitTime())));
            pool.offer(cab);
            poolOfCabsByCity.put(cab.getGeoLocation().getPrimaryKey(), pool);
        }
        return poolOfCabsByCity;
    }
}
