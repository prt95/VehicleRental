package com.navi.rental.commands;

import com.navi.rental.VehicleType;
import com.navi.rental.exceptions.InvalidCommandParameterException;
import com.navi.rental.service.impl.BookingServiceImpl;
import com.navi.rental.storage.BookingDao;
import com.navi.rental.storage.BranchDao;
import com.navi.rental.storage.VehicleDao;

public class BookCommand extends Command {
    private VehicleType vehicleType;
    private long startTime;
    private long endTime;

    private BookingServiceImpl bookingService = new BookingServiceImpl(BranchDao.getInstance(), BookingDao.getInstance(), VehicleDao.getInstance());

    @Override
    public boolean parse(String[] params) throws InvalidCommandParameterException {

        if (params.length < 4) {
            throw new InvalidCommandParameterException("please provide expected parameters");
        }
        try {
            super.parse(params);
            vehicleType = VehicleType.valueOf(params[1].toUpperCase());
            startTime = Long.valueOf(params[2]);
            endTime = Long.valueOf(params[3]);

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
        long price = bookingService.book(getBranchName(), vehicleType, startTime, endTime);
        System.out.println(price);
        return true;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

}
