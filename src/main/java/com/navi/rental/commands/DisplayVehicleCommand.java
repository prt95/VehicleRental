package com.navi.rental.commands;

import com.navi.rental.Vehicle;
import com.navi.rental.exceptions.InvalidCommandParameterException;
import com.navi.rental.service.impl.BookingServiceImpl;
import com.navi.rental.storage.BookingDao;
import com.navi.rental.storage.BranchDao;
import com.navi.rental.storage.VehicleDao;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

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
        super.validateBranchExists(getbranchId());
        return true;
    }

    @Override
    public boolean execute() {
        List<Vehicle> vehicles = bookingService.listVehicles(getbranchId(), startTime, endTime);
        System.out.println(StringUtils.join(vehicles.stream().map(vehicle -> vehicle.getId())
                .collect(Collectors.toList()), ", "));
        return true;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
}
