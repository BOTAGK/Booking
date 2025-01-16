package BookingService;

import Booking.BookingId;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private String username;
    private String name;
    private String lastname;
    private String email;
    private String password;

    private ArrayList<BookingId> bookingIds;

    public User(String name, String lastname, String email, String username, String password) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;

        bookingIds = new ArrayList<BookingId>();
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
    public ArrayList<BookingId> getBookingIds() {
        return bookingIds;
    }

    public void addBooking(BookingId id) {
        bookingIds.add(id);
    }
    public void removeBooking(BookingId id) {
        bookingIds.remove(id);
    }
    public Boolean hasBooking(BookingId id) {
        if(bookingIds.contains(id)) {
            return true;
        }
        return false;
    }
}
