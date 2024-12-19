package SearchStrategy;

import Booking.Booking;

import java.util.ArrayList;
import java.util.List;

public interface SearchStrategy {
    ArrayList<Booking> search(ArrayList<Booking> bookings);

}
