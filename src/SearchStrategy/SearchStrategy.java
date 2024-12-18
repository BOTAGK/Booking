package SearchStrategy;

import Booking.Booking;
import java.util.List;

public interface SearchStrategy {
    List<Booking> search(List<Booking> bookings);

}
