package com.navi.rental.service.impl;

import com.navi.rental.Vehicle;
import com.navi.rental.service.VehicleService;
import com.navi.rental.storage.VehicleDao;

import java.util.List;

public class VehicleServiceImpl implements VehicleService {
    private VehicleDao vehicleDao;

    public VehicleServiceImpl(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    @Override
    public List<Vehicle> getVehicles() {
        return vehicleDao.getAll();
    }

    @Override
    public List<Vehicle> getVehicleByBranchId(String branchId) {
        return vehicleDao.getVehicleByBranchId(branchId);
    }

    @Override
    public boolean addVehicleToBranch(String branchId, Vehicle vehicle) {
        vehicle.setBranchId(branchId);
        return vehicleDao.save(vehicle);
    }
}
