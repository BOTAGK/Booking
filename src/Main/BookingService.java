package Main;

import Booking.Booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import FilterStrategy.*;
import SearchStrategy.*;

public class BookingService {

    private static BookingService instance;
    private HashMap<String,Booking> bookings;
    private SearchStrategy strategy;
    private List<FilterStrategy> filterStrategies;


    private BookingService() {
        this.bookings = new HashMap<String,Booking>();
        this.strategy = new TypeSearchStrategy();
        this.filterStrategies = new ArrayList<>();
    }

    public static BookingService getInstance() {
        if (instance == null) {
            instance = new BookingService();
        }
        return instance;
    }

    private String createId() {
        String temp;
        do {
            temp = String.valueOf((int)(Math.random()*2000000000));
        }while (bookings.containsKey(temp));
        return temp;
    }

    public void createBooking(Booking booking) { //Dodaje nowy booking do listy
        bookings.put(createId(), booking);
    }

    public void deleteBooking(String bookingId) { // Usuwa booking z listy
        bookings.remove(bookingId);
    }

    public void bookBooking(User user, String bookingId) { //Użytkownik rezerwuje booking z tym id
        Booking booking = this.bookings.get(bookingId);
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

        Booking booking = this.bookings.get(bookingId);
        if(booking == null) {
            return;
        }

        booking.setAvailable(true);
        user.removeId(bookingId);
    }

    public ArrayList<Booking> getBookings() { //zwraca ArrayListe wszystkich bookingów
        return new ArrayList<>(this.bookings.values());
    }

    public ArrayList<Booking> getUserBookings(User user) { // zwraca ArrayListe bookingów konkretnego użytkownika
        ArrayList<Booking> bookings = new ArrayList<>();
        for(String id : user.getBookingIds()) {
            bookings.add(this.bookings.get(id));
        }
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

