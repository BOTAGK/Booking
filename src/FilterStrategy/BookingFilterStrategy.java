package FilterStrategy;

import Booking.Booking;

import java.util.List;

public interface BookingFilterStrategy {
    List<Booking> filter(List<Booking> bookings);
}
