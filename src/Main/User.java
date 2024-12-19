package Main;

import Booking.Booking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class User {

    String id;
    String name;
    String lastname;
    String email;

    HashSet<String> bookingIds;

    public User(String id, String name, String lastname, String email) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;

        bookingIds = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean hasBooking(String bookingId) {
        return bookingIds.contains(bookingId);
    }

    public void addBooking(String bookingId) {
        bookingIds.add(bookingId);
    }

    public void removeBooking(String bookingId) {
        bookingIds.remove(bookingId);
    }

}
