package com.navi.rental.storage;

import com.navi.rental.Booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingDao implements Dao {
    private static BookingDao bookingDao = new BookingDao();
    List<Booking> bookings = new ArrayList<>();

    public static BookingDao getInstance() {
        return bookingDao;
    }

    @Override
    public <T> Optional<T> get(String id) {

        return Optional.empty();
    }

    @Override
    public List<Booking> getAll() {
        return bookings;
    }

    @Override
    public <T> boolean save(T t) {
        bookings.add((Booking) t);
        return true;
    }

    @Override
    public <T> void delete(T t) {
    }

    @Override
    public <T> void update(T t, String[] params) {

    }

    public Optional<Booking> get(int id) {
        return bookings.stream().filter(booking -> booking.getBookingId() == id).findFirst();
    }

    public List<Booking> getBookingsByVehicleId(String vehicleId) {
        List<Booking> bookings = getAll();
        return bookings.stream().filter(booking -> booking.getVehicle().getId().equals(vehicleId)).collect(Collectors.toList());
    }
}
