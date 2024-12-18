package SearchStrategy;

import Booking.Booking;
import java.util.List;

public class PriceRangeStrategy implements SearchStrategy {

    @Override
    public List<Booking> search(List<Booking> bookings) {
        return bookings;
    }
}
