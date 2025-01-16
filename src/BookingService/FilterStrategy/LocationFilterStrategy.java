package BookingService.FilterStrategy;

import Booking.Booking;
import BookingService.BookingId;
import Util.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class LocationFilterStrategy implements FilterStrategy{

    private List<String> locations;

    public LocationFilterStrategy(List<String> locations) {
        this.locations = locations;
    }

    @Override
    public List<Pair<Booking, BookingId>> filter(List<Pair<Booking, BookingId>> entries) {
        return entries.stream()
            .filter(entry -> locations.contains(entry.first.getLocation()))
            .collect(Collectors.toList());
    }
}
