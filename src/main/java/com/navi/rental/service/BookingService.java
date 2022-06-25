package com.navi.rental.service;

import com.navi.rental.Vehicle;
import com.navi.rental.VehicleType;

import java.util.List;

public interface BookingService {
    long book(String branchId, VehicleType vehicleType, long startTime, long endTime);

    List<Vehicle> listVehicles(String branchId, long startTime, long endTime);
}
