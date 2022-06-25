package com.navi.rental.service;

import com.navi.rental.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> getVehicles();

    List<Vehicle> getVehicleByBranchId(String branchId);

    boolean addVehicleToBranch(String branchId, Vehicle vehicle);
}
