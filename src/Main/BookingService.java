package Main;

import Booking.Booking;
import java.util.ArrayList;
import java.util.Scanner;

import SearchStrategy.*;

public class BookingService {
    private ArrayList<Booking> bookings;
    private ArrayList<User> observers;
    private SearchStrategy strategy;

    public BookingService() {
        bookings = new ArrayList<Booking>();
        observers = new ArrayList<User>();
        strategy = new TypeSearchStrategy();
    }

    public void createBooking(Booking booking) {
        bookings.add(booking);
    }

    public void addBooking(User user) {

        System.out.println("Which booking do you want to book?\n");
        System.out.print("Insert the correct booking Id: ");
        showBookings();
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();

        for(Booking booking : bookings) {
            if(booking.getId().equals(id) && booking.isFree()) {
                user.addBooking(booking.getId());
                booking.book(user);
                return;
            }
        }
        System.out.println("Booking id: " + id + " not found");
    }

    public void cancelBooking(User user) {

        System.out.println("Which booking do you want to remove?\n");
        System.out.print("Insert the correct booking Id: ");
        showBookings(user);
        Scanner sc = new Scanner(System.in);
        String id;
        id = sc.nextLine();

        for(Booking booking : bookings) {
            if(booking.getId().equals(id)) {
                booking.cancelBooking(user);
                user.removeBooking(id);
                return;
            }
        }
    }


    public void showBookings(User user) {
        for(Booking booking : bookings) {
            if(user.hasBooking(booking.getId())) {
                System.out.println(booking);
            }
        }
    }
    public void showBookings() {
        for(Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public ArrayList<User> getObservers() {
        return observers;
    }

    public void setStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public void showBookings (SearchStrategy strategy){
        bookings =(ArrayList<Booking>)strategy.search(bookings);
        for (int i = 0; i < bookings.size(); i++) {
            System.out.println(bookings.get(i));
        }
    }
}

