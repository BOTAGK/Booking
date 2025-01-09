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
    private SearchStrategy strategy;
    private List<FilterStrategy> filterStrategies;


    private BookingService() {
        this.bookings = new ArrayList<Booking>();
        this.strategy = new TypeSearchStrategy();
        this.filterStrategies = new ArrayList<>();
    }

    private Booking findBooking(String id) {
        for (Booking booking : this.bookings) {
            if (booking.getId().equals(id)) {
                return booking;
            }
        }
        return null;
    }

    public static BookingService getInstance() {
        if (instance == null) {
            instance = new BookingService();
        }
        return instance;
    }

    public void createBooking(Booking booking) { //Dodaje nowy booking do listy
        bookings.add(booking);
    }

    public void deleteBooking(String bookingId) { // Usuwa booking z listy
        for (int i = 0;i < bookings.size(); i++) {
            if (bookings.get(i).getId().equals(bookingId)) {//todo usunac id z booking i dac indentyfikacje do bookingservice
                bookings.remove(i);
                return;
            }
        }
    }

    public void bookBooking(User user, String bookingId) { //Użytkownik rezerwuje booking z tym id
        Booking booking = this.findBooking(bookingId);
        if (booking == null) {
            return;
        }

        if(booking.getAvailable()) {
            booking.setAvailable(false);
            user.addId(bookingId);
        }
    }

    public void unBookBooking(User user, String bookingId) { // Użytkownik odrezerwowuje booking z tym id
        if(!user.hasBooking(bookingId)) {
            return;
        }
        Booking booking = this.findBooking(bookingId);
        if(booking == null) {
            return;
        }

        booking.setAvailable(true);
        user.removeId(bookingId);
    }

    public void showUserBookings(User user) { // Wyświetla wszystkie bookingi danego użytkownika
        for (Booking booking : bookings) {
            if(user.hasBooking(booking.getId())) {
                System.out.println(booking);
            }
        }
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(ArrayList<Booking> bookings) { this.bookings = bookings; }

    public void showBookings (){
        bookings = strategy.search(bookings);
        for (int i = 0; i < bookings.size(); i++) {
            System.out.println(bookings.get(i));
        }
    }

    public void setStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
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

