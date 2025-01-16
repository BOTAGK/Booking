package BookingService.FilterStrategy;

import Booking.Booking;
import Booking.CarRentalBooking;
import BookingService.BookingId;
import Util.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class CarRentalFilterStrategy implements FilterStrategy {
    private List<String> carTypes;

    public CarRentalFilterStrategy() {}

    public CarRentalFilterStrategy(List<String> carTypes) {
        this.carTypes = carTypes;
    }

    @Override
    public List<Pair<Booking, BookingId>> filter(List<Pair<Booking, BookingId>> entries) {
        return entries.stream()
            .filter(entry -> entry.second.matchesType(new BookingId(BookingId.Prefix.CRT)))
            .filter(entry -> carTypes==null || carTypes.contains(((CarRentalBooking)entry.first).getCarType()))
            .collect(Collectors.toList());
    }
}
