package BookingService;

import Booking.Booking;
import BookingService.FilterStrategy.*;
import Util.Pair;

import java.util.ArrayList;
import java.util.List;

public class BookingService {

    private static BookingService instance;
    private ArrayList<Pair<Booking, BookingId>> entries;

    private BookingService() {
        this.entries = new ArrayList<>();
    }

    public static BookingService getInstance() {
        if (instance == null) {
            instance = new BookingService();
        }
        return instance;
    }

    // Creates a new Booking in the service by assigning a unique identifier
    public void createBooking(Booking booking) {
        this.entries.add(new Pair<>(booking, makeUniqueId(booking.getIdPrefix())));
    }

    // For bulk creation
    public void createBookings(List<Booking> bookings) {
        for (Booking booking : bookings) {
            createBooking(booking);
        }
    }

    // Removes a Booking if the given id matches
    public void deleteBooking(BookingId id) {
        for (int i = 0; i < this.entries.size(); i++) {
            if (this.entries.get(i).second.equals(id)) {
                this.entries.remove(i);
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

        if (!booking.getAvailable()) {
            return false;
        }

        booking.setAvailable(false);
        user.addBooking(id);

        return true;
    }

    public Booking getBooking(BookingId id) {
        for (Pair<Booking, BookingId> entry : this.entries) {
            if (entry.second.equals(id)) {
                return entry.first;
            }
        }
        return null;
    }

    // Unbooks a Booking for the given User, making it available
    public void unbookBooking(User user, BookingId id) {
        if (!user.hasBooking(id)) {
            return;
        }
        Booking booking = getBooking(id);
        if (booking == null) {
            return;
        }

        user.removeBooking(id);
        booking.setAvailable(true);
    }

    public void printUserBookings(User user) {
        for (Pair<Booking, BookingId> entry : this.entries) {
            if (user.hasBooking(entry.second)) {
                System.out.println(entry.first);
            }
        }
    }

    public void printBookings() {
        for (int i = 0; i < this.entries.size(); i++) {
            System.out.println(this.entries.get(i).first);
        }
    }

    public List<Booking> getBookings() {
        return getBookings(this.entries);
    }

    public List<Booking> filterBookings(List<FilterStrategy> filterStrategies) {
        // Initialize with full list
        List<Pair<Booking, BookingId>> filteredEntries = this.entries;
        // Apply filters consecutively
        for (FilterStrategy filterStrategy : filterStrategies) {
            filteredEntries = filterStrategy.filter(filteredEntries);
        }

        return getBookings(filteredEntries);
    }

    // Create a unique id for the given Booking
    private BookingId makeUniqueId(BookingId.Prefix prefix) {
        boolean failbit;
        long index;
        // Find an unused index
        for (index = 0; index >= 0; ++index) {
            failbit = false;

            // Check whether any Booking of the same type had used the index
            for (Pair<Booking, BookingId> entry : this.entries) {
                if (entry.second.equals(new BookingId(prefix, index))) {
                    failbit = true;
                }
            }

            if (!failbit) {
                return new BookingId(prefix, index);
            }
        }

        throw new IndexOutOfBoundsException("That's clearly too many bookings");
    }

    public static List<Booking> getBookings(List<Pair<Booking, BookingId>> entries) {
        List<Booking> bookings = new ArrayList<>();

        for (Pair<Booking, BookingId> entry : entries) {
            bookings.add(entry.first);
        }

        return bookings;
    }

    public BookingId getBookingId(Booking booking) {
        for (Pair<Booking, BookingId> entry : this.entries) {
            if (entry.first.equals(booking)) {
                return entry.second;
            }
        }
        return null;
    }
}
