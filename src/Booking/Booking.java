package Booking;

import Main.User;

public abstract class Booking {
    private String id;
    private String name;
    private String location;
    private double price;
    private Boolean status = true;

    private String startDate;
    private String endDate;
    private User user = null;

    public Booking(String id, String name, String location, double price, Boolean status) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.price = price;
        this.status = status;
    }

    public String getId() {
        return id;
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
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }



}
