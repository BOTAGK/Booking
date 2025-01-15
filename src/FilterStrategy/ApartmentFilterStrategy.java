package FilterStrategy;

import Booking.ApartmentBooking;
import Booking.Booking;

import java.util.List;
import java.util.stream.Collectors;

public class ApartmentFilterStrategy implements FilterStrategy {
    private int minRoomCount;
    private int minRating;

    public ApartmentFilterStrategy(int minRoomCount, int minRating) {
        this.minRoomCount = minRoomCount;
        this.minRating = minRating;
    }

    @Override
    public List<Booking> filter(List<Booking> bookings) {
        return bookings.stream()
                .filter(booking -> booking.getIdType().equals(new ApartmentBooking().getIdType()))
                .filter(booking -> ((ApartmentBooking) booking).getRoomCount() >= minRoomCount)
                .filter(booking -> ((ApartmentBooking) booking).getRating() >= minRating)
                .collect(Collectors.toList());
    }
}
