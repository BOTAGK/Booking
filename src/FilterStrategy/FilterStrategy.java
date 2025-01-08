package FilterStrategy;

import Booking.Booking;

import java.util.List;

public interface FilterStrategy {
    List<Booking> filter(List<Booking> bookings);
}
