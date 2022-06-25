package com.navi.rental.commands;

import com.navi.rental.VehicleType;
import com.navi.rental.exceptions.InvalidCommandParameterException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddBranchCommand extends Command {
    private List<VehicleType> vehicleTypeList;

    public List<VehicleType> getVehicleTypeList() {
        return vehicleTypeList;
    }

    public void setVehicleTypeList(List<VehicleType> vehicleTypeList) {
        this.vehicleTypeList = vehicleTypeList;
    }


    @Override
    public boolean parse(String[] params) throws InvalidCommandParameterException {
        if (params.length < 2) {
            throw new InvalidCommandParameterException("please provide vehicle types");
        }
        try {
            super.parse(params);
            this.vehicleTypeList = Arrays.stream(params[1].split(",")).
                    map(type -> VehicleType.valueOf(type.toUpperCase())).collect(Collectors.toList());
        } catch (Exception ex) {
            throw new InvalidCommandParameterException("please provide vehicle types");
        }
        return true;
    }

    @Override
    public boolean validate() {
        if (getBranchService().getBranchById(getBranchName()).isPresent()) {
            System.out.println("Branch already exists");
            return false;
        }
        return true;
    }

    @Override
    public boolean execute() {
        boolean result = getBranchService().onboardBranch(getBranchName(), vehicleTypeList);
        System.out.println(result);
        return true;
    }


}
