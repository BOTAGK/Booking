package FilterStrategy;

import Booking.Booking;

import java.util.List;
import java.util.stream.Collectors;

public class LocationFilterStrategy implements FilterStrategy{

    private List<String> locations;

    public LocationFilterStrategy(List<String> locations) {
        this.locations = locations;
    }

    @Override
    public List<Booking> filter(List<Booking> bookings) {
        return bookings.stream()
                .filter(booking -> locations.contains(booking.getLocation()))
                .collect(Collectors.toList());
    }
}
