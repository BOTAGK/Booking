package Booking;

import java.io.Serializable;
import java.time.LocalDate;

import BookingService.BookingId;
import BookingService.User;

public abstract class Booking implements Serializable {
    private String name;
    private String location;
    private double price;
    private boolean available;

    private LocalDate startDate;
    private LocalDate endDate;
    private User user = null;

    public Booking() {}

    public Booking(String name, String location, double price, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.available = true;
    }
    public String getName() {
        return name;
    }
    public String getLocation() {
        return location;
    }
    public double getPrice() {
        return price;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public boolean getAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract BookingId.Prefix getIdPrefix();

    public String toString() {
        return  "Name: " + name + "\n" +
                "Location: " + location + "\n" +
                "Price: " + price + "\n" +
                "StartDate: " + startDate.toString() + "\n" +
                "EndDate: " + endDate.toString() + "\n";
    }
}
