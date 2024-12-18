package Main;

import Booking.Booking;
import java.util.ArrayList;

public class BookingService {
    private ArrayList<Booking> bookings;
    private ArrayList<User> observers;
    //private SearchStrategy strategy;

    public BookingService() {

    }

    public Booking createBooking(User user, Booking booking, String startDate, String endDate) {
        booking.setUser(user);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        bookings.add(booking);
        return booking;
    }

    public void cancelBooking(String bookingId) {
        boolean found = false;
        for(int i = 0; !found && i < bookings.size(); i++){
            if(bookings.get(i).getId().equals(bookingId)){
                bookings.remove(i);
                found = true;
            }
        }
    }

    public ArrayList<Booking>  getBookingsForUser(User user) {
        return user.getBookings();
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public ArrayList<User> getObservers() {
        return observers;
    }

    /*
    public void setStrategy(SearchStrategy strategy){
        this.strategy = strategy;
    }

    public void showBookings(SearchStrategy strategy) {
        bookings = strategy.search(bookings);
        for(int i = 0; i < bookings.size(); i++){
            System.out.println(bookings.get(i));
        }
    }
     */


}
