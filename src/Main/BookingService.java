package Main;

import Booking.Booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import FilterStrategy.*;
import SearchStrategy.*;

public class BookingService {

    private static BookingService instance;
    private ArrayList<Booking> bookings;
    private ArrayList<User> observers;
    private SearchStrategy strategy;
    private List<FilterStrategy> filterStrategies;


    private BookingService() {
        this.bookings = new ArrayList<Booking>();
        this.observers = new ArrayList<User>();
        this.strategy = new TypeSearchStrategy();
        this.filterStrategies = new ArrayList<>();
    }

    public static BookingService getInstance() {
        if (instance == null) {
            instance = new BookingService();
        }
        return instance;
    }

    public Booking createBooking(User user, Booking booking, LocalDate startDate, LocalDate endDate) {
        booking.setUser(user);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        return booking;
    }

    public void cancelBooking(String bookingId) {
        boolean found = false;
        for (int i = 0; !found && i < bookings.size(); i++) {
            if (bookings.get(i).getId().equals(bookingId)) {//todo usunac id z booking i dac indentyfikacje do bookingservice
                bookings.remove(i);
                found = true;
            }
        }
    }

    public ArrayList<Booking> getBookingsForUser(User user) {
        return user.getBookings();
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(ArrayList<Booking> bookings) { this.bookings = bookings; }

    public ArrayList<User> getObservers() {
        return observers;
    }

    public void setStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public void showBookings (){
        bookings = strategy.search(bookings);
        for (int i = 0; i < bookings.size(); i++) {
            System.out.println(bookings.get(i));
        }
    }

//    filter strategy
    public void setFilterStrategies(List<FilterStrategy> filterStrategies) {
        this.filterStrategies = filterStrategies;
    }

    public List<Booking> filterBookings(){
        List<Booking> filteredBookings = new ArrayList<>(bookings);
        for (FilterStrategy filterStrategy : filterStrategies) {
            filteredBookings = filterStrategy.filter(filteredBookings);
        }
        return filteredBookings;
    }
}

