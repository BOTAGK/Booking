package Main;

import Booking.Booking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class User implements Serializable {

    private String username;
    private String name;
    private String lastname;
    private String email;
    private String password;

    private ArrayList<String> bookingIds;

    public User( String name, String lastname, String email, String username, String password) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;

        bookingIds = new ArrayList<String>();
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() { return lastname; }
    public void setLastname(String lastname) {this.lastname = lastname; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void addId(String id) {
        bookingIds.add(id);
    }
    public void removeId(String id) {
        bookingIds.remove(id);
    }
    public Boolean hasBooking(String id) {
        if(bookingIds.contains(id)) {
            return true;
        }
        return false;
    }
}
