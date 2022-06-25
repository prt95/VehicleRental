package com.navi.rental.storage;

import com.navi.rental.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VehicleDao implements Dao {

    private static VehicleDao vehicleDao = new VehicleDao();
    List<Vehicle> vehicles = new ArrayList<>();

    public static VehicleDao getInstance() {
        return vehicleDao;
    }

    @Override
    public <T> Optional<T> get(String id) {
        return Optional.empty();
    }

    @Override
    public List<Vehicle> getAll() {
        return vehicles;
    }

    @Override
    public <T> boolean save(T t) {
        vehicles.add((Vehicle) t);
        return true;
    }

    @Override
    public <T> void delete(T t) {

    }

    @Override
    public <T> void update(T t, String[] params) {
        Vehicle vehicle = (Vehicle) t;
        int idx = 0;
        for (Vehicle v : vehicles) {

            if (v.getId().equals(vehicle.getId())) {
                break;
            }
            idx++;
        }
        vehicles.remove(idx);
        vehicles.add(vehicle);
    }

    public List<Vehicle> getVehicleByBranchId(String branchId) {
        return vehicles.stream().filter(vehicle -> vehicle.getBranchId().equals(branchId)).collect(Collectors.toList());
    }
}
