package com.navi.rental.commands;

import com.navi.rental.Vehicle;
import com.navi.rental.exceptions.InvalidCommandParameterException;
import com.navi.rental.service.impl.BookingServiceImpl;
import com.navi.rental.storage.BookingDao;
import com.navi.rental.storage.BranchDao;
import com.navi.rental.storage.VehicleDao;

import java.util.List;

public class DisplayVehicleCommand extends Command {
    private long startTime;
    private long endTime;

    private BookingServiceImpl bookingService = new BookingServiceImpl(BranchDao.getInstance(), BookingDao.getInstance(), VehicleDao.getInstance());

    @Override
    public boolean parse(String[] params) throws InvalidCommandParameterException {
        if (params.length < 3) {
            throw new InvalidCommandParameterException("please provide expected parameters");
        }
        try {
            super.parse(params);
            startTime = Long.valueOf(params[1]);
            endTime = Long.valueOf(params[2]);

        } catch (Exception ex) {
            throw new InvalidCommandParameterException("please provide expected parameters");
        }
        return true;
    }

    @Override
    public boolean validate() {
        super.validateBranchExists(getBranchName());
        return true;
    }

    @Override
    public boolean execute() {
        List<Vehicle> vehicles = bookingService.listVehicles(getBranchName(), startTime, endTime);
        vehicles.stream().forEach(v -> System.out.print(v.getId() + ", "));
        System.out.println();
        return true;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
}
