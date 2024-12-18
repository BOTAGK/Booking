package SearchStrategy;

import Booking.Booking;
import java.util.List;

public class TypeSearchStrategy implements SearchStrategy {
    @Override
    public List<Booking> search(List<Booking> bookings) {
        return bookings;
    }
}
