package BookingService.FilterStrategy;

import Booking.Booking;
import BookingService.BookingId;
import Util.Pair;

import java.util.List;

public interface FilterStrategy {
    List<Pair<Booking, BookingId>> filter(List<Pair<Booking, BookingId>> entries);
}
