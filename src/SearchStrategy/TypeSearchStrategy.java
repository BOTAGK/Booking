package SearchStrategy;

import Booking.Booking;

import java.util.ArrayList;
import java.util.List;

public class TypeSearchStrategy implements SearchStrategy {
    @Override
    public ArrayList<Booking> search(ArrayList<Booking> bookings) {
        return bookings;
    }
}
