package com.cab.management.booking.dao;

import com.cab.management.booking.enums.Status;
import com.cab.management.booking.exceptions.CabNotAvailableException;
import com.cab.management.booking.model.Cab;
import com.cab.management.booking.model.GeoLocation;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class BookingDao {

    private final Map<String, PriorityQueue<Cab>> cityWiseAvailableCab;
    private final Map<String, Cab> bookedCab = new HashMap<>();

    public BookingDao(final Map<String, PriorityQueue<Cab>> cityWiseAvailableCab) {
        this.cityWiseAvailableCab = cityWiseAvailableCab;
    }

    public Cab bookCab(final GeoLocation userLocation) {
        var availableCabs = cityWiseAvailableCab.getOrDefault(userLocation.getPrimaryKey(),
            new PriorityQueue<>((cab1, cab2) -> (int) (cab2.waitTime() - cab1.waitTime())));
        if (availableCabs.isEmpty()) {
            throw new CabNotAvailableException("currently no cab is available at your location.");
        }
        Cab allocatingCab = availableCabs.poll();
        allocatingCab = allocatingCab.toBuilder().pickupTime(Instant.now().toEpochMilli())
            .status(Status.ON_TRIP).build();
        bookedCab.put(allocatingCab.getId(), allocatingCab);
        return allocatingCab;
    }

    public void releaseCab(final String cabId) {
        Cab allocatedCab = bookedCab.remove(cabId);
        allocatedCab = allocatedCab.toBuilder().lastUpdateTime(Instant.now().toEpochMilli())
            .status(Status.IDLE).build();
        String cabLocation = allocatedCab.getGeoLocation().getPrimaryKey();
        PriorityQueue<Cab> availableCabs = cityWiseAvailableCab.get(cabLocation);
        availableCabs.offer(allocatedCab);
        cityWiseAvailableCab.put(cabLocation, availableCabs);
    }

    public Map<String, List<Cab>> getCabsOnTrip() {
        return bookedCab.values().stream()
            .collect(groupingBy(cab -> cab.getGeoLocation().getPrimaryKey(), Collectors.toList()));
    }

    public List<Cab> getAllCabs() {
        List<Cab> cabs = new ArrayList<>();
        for (Map.Entry<String, PriorityQueue<Cab>> entry : cityWiseAvailableCab.entrySet()) {
            for (Cab cab : entry.getValue()) {
                cabs.add(cab);
            }
        }
        return Collections.unmodifiableList(cabs);
    }
}
