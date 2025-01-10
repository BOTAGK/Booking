package Booking;

import Main.User;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Booking implements Serializable {
    private String name;
    private String location;
    private double price;
    private Boolean available;

    private LocalDate startDate;
    private LocalDate endDate;
    private User user = null;

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
    public Boolean getAvailable() {
        return available;
    }
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String toString() {
        return  "Name: " + name + "\n" +
                "Location: " + location + "\n" +
                "Price: " + price + "\n" +
                "StartDate: " + startDate.toString() + "\n" +
                "EndDate: " + endDate.toString() + "\n";
    }



}
