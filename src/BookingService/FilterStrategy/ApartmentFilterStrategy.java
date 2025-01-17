package BookingService.FilterStrategy;

import Booking.ApartmentBooking;
import Booking.Booking;
import BookingService.BookingId;
import Util.Pair;

import java.util.ArrayList;
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
        List<Pair<Booking, BookingId>> filteredBookings= new ArrayList<>();
        for(Pair<Booking, BookingId> pair: entries) {
            if(pair.first instanceof ApartmentBooking) {
                ApartmentBooking apartmentBooking = (ApartmentBooking) pair.first;
                if(apartmentBooking.getRoomCount() >= minRoomCount && apartmentBooking.getRating() >= minRating) {
                    filteredBookings.add(pair);
                }
            }
        }
        return filteredBookings;
    }
}
