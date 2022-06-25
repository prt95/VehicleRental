package com.navi.rental;

public class Booking {
    private User user;
    private Vehicle vehicle;
    private long price;
    private long startTime;
    private long endTime;
    private int bookingId;


    public Booking(Vehicle vehicle, long price, long startTime, long endTime, int bookingId) {
        this.vehicle = vehicle;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookingId = bookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
}
