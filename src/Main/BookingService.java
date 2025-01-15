package Main;

import Booking.Booking;
import Booking.BookingId;
import FilterStrategy.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookingService {

    private static BookingService instance;
    private ArrayList<Booking> bookings;

    private BookingService() {
        this.bookings = new ArrayList<>();
    }

    public static BookingService getInstance() {
        if (instance == null) {
            instance = new BookingService();
        }
        return instance;
    }

    // Creates a new Booking in the service by assigning a unique identifier
    public void createBooking(Booking booking) {
        booking.assignId(createUniqueId(booking));
        bookings.add(booking);
    }

    public void createBookings(List<Booking> bookings) {
        for(Booking booking : bookings) {
            createBooking(booking);
        }
    }

    // Removes a Booking if the given id matches
    public void deleteBooking(BookingId id) {
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getId().equals(id)) {
                bookings.remove(i);
                return;
            }
        }
    }

    // Books a Booking for the given User, making it unavailable
    // Returns whether the operation was successful (if the Booking was available)
    public boolean bookBooking(User user, BookingId id) {
        Booking booking = getBooking(id);
        if (booking == null) {
            throw new IllegalArgumentException("There is no Booking with such BookingId: " + id.toString());
        }

        if(!booking.getAvailable()) {
            return false;
        }

        booking.setAvailable(false);
        user.addBooking(id);

        return true;
    }

    private Booking getBooking(BookingId id) {
        for (Booking booking : this.bookings) {
            if (booking.getId().equals(id)) {
                return booking;
            }
        }
        return null;
    }

    // Unbooks a Booking for the given User, making it available
    public void unbookBooking(User user, BookingId id) {
        if(!user.hasBooking(id)) {
            return;
        }
        Booking booking = getBooking(id);
        if(booking == null) {
            return;
        }

        user.removeBooking(id);
        booking.setAvailable(true);
    }

    public void printUserBookings(User user) {
        for (Booking booking : this.bookings) {
            if(user.hasBooking(booking.getId())) {
                System.out.println(booking);
            }
        }
    }

    public void printBookings (){
        for (int i = 0; i < this.bookings.size(); i++) {
            System.out.println(this.bookings.get(i));
        }
    }

    public List<Booking> getBookings() {
        List<Booking> bookings = new ArrayList<>();
        for(Booking booking : this.bookings) {
            bookings.add(booking);
        }

        return bookings;
    }

    public List<Booking> filterBookings(List<FilterStrategy> filterStrategies) {
        List<Booking> filteredBookings = getBookings();
        for (FilterStrategy filterStrategy : filterStrategies) {
            filteredBookings = filterStrategy.filter(filteredBookings);
        }

        return filteredBookings;
    }

    // Create a unique id for the given Booking
    private BookingId createUniqueId(Booking booking)
    {
        boolean failbit;
        long index;
        // Find an unused index
        for(index = 0; index >= 0; ++index)
        {
            failbit = false;

            // Check whether any Booking of the same type had used the index
            for(Booking listedBooking : this.bookings)
            {
                if((new BookingId(booking.getIdType(), index)).equals(listedBooking.getId()))
                { failbit = true; }
            }

            if(!failbit)
            { return new BookingId(booking.getIdType(), index); }
        }

        throw new IndexOutOfBoundsException("That's clearly too many bookings");
    }
}
