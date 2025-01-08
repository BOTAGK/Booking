package FilterStrategy;

import Booking.ApartmentBooking;
import Booking.Booking;

import java.util.List;
import java.util.stream.Collectors;

public class ApartmentFilterStrategy implements BookingFilterStrategy {
    private Integer minRoomCount;
    private Integer minRating;

    public ApartmentFilterStrategy(Integer minRoomCount, Integer minRating) {
        this.minRoomCount = minRoomCount;
        this.minRating = minRating;
    }

    @Override
    public List<Booking> filter(List<Booking> bookings) {
        return bookings.stream()
                .filter(booking -> booking instanceof ApartmentBooking)
                .filter(booking -> minRoomCount==null || ((ApartmentBooking) booking).getRoomCount() >= minRoomCount)
                .filter(booking -> minRating==null || ((ApartmentBooking) booking).getRating() >= minRating)
                .collect(Collectors.toList());
    }
}
