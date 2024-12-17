package Main;

import Booking.Booking;
import java.util.ArrayList;

public class BookingService {
    private ArrayList<Booking> bookings;
    private ArrayList<User> observers;
    //private SearchStrategy strategy;

    public BookingService() {

    }

    public Booking createBooking(User user, Booking booking, String startDate, String endDate){
        booking.setUser(user);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        return booking;
    }

    public void cancelBooking(String bookingId){

    }

    public ArrayList<Booking>  getBookingsForUser(User user){
        return user.getBookings();
    }

    /*
    public ArrayList<Booking> showBookings(SearchStrategy strategy){
        return strategy.search();
    }
     */
}
