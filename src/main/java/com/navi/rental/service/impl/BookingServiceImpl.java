package com.navi.rental.service.impl;

import com.navi.rental.Booking;
import com.navi.rental.Branch;
import com.navi.rental.Vehicle;
import com.navi.rental.VehicleType;
import com.navi.rental.service.BookingService;
import com.navi.rental.storage.BookingDao;
import com.navi.rental.storage.BranchDao;
import com.navi.rental.storage.VehicleDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {
    private BranchDao branchDao;
    private BookingDao bookingDao;
    private VehicleDao vehicleDao;
    private int bookingId = 0;

    public BookingServiceImpl(BranchDao branchDao, BookingDao bookingDao, VehicleDao vehicleDao) {
        this.branchDao = branchDao;
        this.bookingDao = bookingDao;
        this.vehicleDao = vehicleDao;
    }

    @Override
    public long book(String branchId, VehicleType vehicleType, long startTime, long endTime) {
        Branch branch = branchDao.get(branchId).get();

        List<Vehicle> branchVehicles = vehicleDao.getVehicleByBranchId(branch.getBranchId());

        List<Vehicle> availableVehicles = getAvailableVehicles(branchVehicles, vehicleType, startTime, endTime);
        if (availableVehicles.size() == 0) {
            return -1;
        }

        Vehicle vehicle = availableVehicles.get(0);

        int totalVehiclesCount = getVehiclesByType(branchVehicles, vehicleType).size();
        long price = getDynamicPrice(vehicle.getPrice(), startTime, endTime, availableVehicles.size(), totalVehiclesCount);

        Booking booking = new Booking(vehicle, price, startTime, endTime, bookingId++);
        bookingDao.save(booking);
        vehicleDao.update(vehicle, new String[0]);
        return price;
    }

    @Override
    public List<Vehicle> listVehicles(String branchId, long startTime, long endTime) {
        Optional<Branch> optionalBranch = branchDao.get(branchId);

        if (!optionalBranch.isPresent()) {
            return new ArrayList<>();
        }
        List<Vehicle> branchVehicles = vehicleDao.getVehicleByBranchId(optionalBranch.get().getBranchId());
        return getAvailableVehiclesByTime(branchVehicles, startTime, endTime);
    }


    private List<Vehicle> getAvailableVehicles(List<Vehicle> branchVehicles, VehicleType vehicleType, long startTime, long endTime) {
        List<Vehicle> vehicles = getVehiclesByType(branchVehicles, vehicleType);

        List<Vehicle> freeVehicles = getAvailableVehiclesByTime(vehicles, startTime, endTime);
        return freeVehicles;
    }

    private List<Vehicle> getVehiclesByType(List<Vehicle> vehicles, VehicleType vehicleType) {
        return vehicles.stream().filter(vehicle ->
                vehicle.getVehicleType() == vehicleType).
                collect(Collectors.toList());
    }

    private List<Vehicle> getAvailableVehiclesByTime(List<Vehicle> vehicles, long startTime, long endTime) {
        List<Vehicle> freeVehicles = vehicles.stream()
                .filter(vehicle -> isVehicleAvailableForBooking(vehicle.getId(), startTime, endTime))
                .collect(Collectors.toList());

        Collections.sort(freeVehicles, (vehicle1, vehicle2) ->
                vehicle1.getPrice() - vehicle2.getPrice() == 0 ? 0 :
                        vehicle1.getPrice() - vehicle2.getPrice() < 0 ? -1 : 1);
        return freeVehicles;
    }


    private boolean isVehicleAvailableForBooking(String vehicleID, long startTime, long endTime) {
        List<Booking> bookings = bookingDao.getBookingsByVehicleId(vehicleID);
        boolean isBooked = bookings.stream().anyMatch(booking ->
                !(booking.getStartTime() >= endTime || booking.getEndTime() <= startTime));
        return !isBooked;
    }

    private long getDynamicPrice(long pricePerHour, long startTime, long endTime, int availableVehiclesCount, int totalVehiclesCount) {
        long price = pricePerHour * (endTime - startTime);

        double availableVehiclesPercentage = ((double) availableVehiclesCount / totalVehiclesCount) * 100;
        if (availableVehiclesPercentage < 20) {
            price += price * 0.10;
        }
        return price;
    }
}
