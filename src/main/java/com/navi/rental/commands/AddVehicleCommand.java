package com.navi.rental.commands;

import com.navi.rental.Vehicle;
import com.navi.rental.VehicleType;
import com.navi.rental.exceptions.InvalidCommandParameterException;
import com.navi.rental.service.VehicleService;
import com.navi.rental.service.impl.VehicleServiceImpl;
import com.navi.rental.storage.VehicleDao;

import java.util.List;

public class AddVehicleCommand extends Command {

    private VehicleService vehicleService = new VehicleServiceImpl(VehicleDao.getInstance());

    private VehicleType vehicleType;
    private String vehicleId;
    private long pricePerHour;

    @Override
    public boolean parse(String[] params) throws InvalidCommandParameterException {
        if (params.length < 4) {
            throw new InvalidCommandParameterException("please provide expected parameters");
        }
        try {
            super.parse(params);
            vehicleType = VehicleType.valueOf(params[1].toUpperCase());
            vehicleId = params[2];
            pricePerHour = Long.valueOf(params[3]);
        } catch (Exception ex) {
            throw new InvalidCommandParameterException("please provide expected parameters");
        }
        return true;
    }

    @Override
    public boolean validate() {
        super.validateBranchExists(getBranchName());
        List<Vehicle> vehicleList = vehicleService.getVehicleByBranchId(getBranchName());
        if (vehicleList.stream().anyMatch(v -> v.getId().equals(getVehicleId()))) {
            System.out.println("Vehicle already exists in branch");
            return false;
        }
        //Todo: validate branch Id exists
        return true;
    }

    @Override
    public boolean execute() {
        boolean result = vehicleService.addVehicleToBranch(getBranchName(), new Vehicle(vehicleId, vehicleType, pricePerHour));
        System.out.println(result);
        return result;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public long getPricePerHour() {
        return pricePerHour;
    }

}
