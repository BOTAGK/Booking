package Main;

import Booking.Booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import FilterStrategy.*;

public class BookingService {

    private static BookingService instance;
    private List<Booking> bookings;
    private ArrayList<User> observers;
    private List<FilterStrategy> filterStrategies;


    private BookingService() {
        this.bookings = new ArrayList<Booking>();
        this.observers = new ArrayList<User>();
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

    public List<Booking> getBookingsForUser(User user) {
        return user.getBookings();
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) { this.bookings = bookings; }

    public ArrayList<User> getObservers() {
        return observers;
    }


    public void showBookings (List<Booking> bookings) {
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    public void reserveBooking(User user){
        System.out.println("Enter ID of booking you want to reserve");
        Scanner scanner = new Scanner(System.in);
        String id;
        int indeks = -1;
        while(indeks == -1){
            id = scanner.nextLine();
            for(int i = 0; i < bookings.size(); i++){
                if(bookings.get(i).getId().equals(id)){
                    indeks = i;
                    break;
                }
            }
            if(indeks == -1){
                System.out.println("Please enter valid ID");
            }
        }
        bookings.get(indeks).setUser(user);
        bookings.get(indeks).setAvailable(false);
        user.addBooking(bookings.get(indeks));
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

