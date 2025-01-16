package BookingService.FilterStrategy;

import Booking.ApartmentBooking;
import Booking.Booking;
import BookingService.BookingId;
import Util.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class ApartmentFilterStrategy implements FilterStrategy {
    private int minRoomCount;
    private int minRating;

    public ApartmentFilterStrategy() {}

    public ApartmentFilterStrategy(int minRoomCount, int minRating) {
        this.minRoomCount = minRoomCount;
        this.minRating = minRating;
    }

    @Override
    public List<Pair<Booking, BookingId>> filter(List<Pair<Booking, BookingId>> entries) {
        return entries.stream()
            .filter(entry -> entry.second.matchesType(new BookingId(BookingId.Prefix.APT)))
            .filter(entry -> ((ApartmentBooking)entry.first).getRoomCount() >= minRoomCount)
            .filter(entry -> ((ApartmentBooking)entry.first).getRating() >= minRating)
            .collect(Collectors.toList());
    }
}
