package BookingService.FilterStrategy;

import Booking.Booking;
import BookingService.BookingId;
import Util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LocationFilterStrategy implements FilterStrategy{

    private List<String> locations;

    public LocationFilterStrategy(List<String> locations) {
        this.locations = locations;
    }

    @Override
    public List<Pair<Booking, BookingId>> filter(List<Pair<Booking, BookingId>> entries) {
        List<Pair<Booking, BookingId>> filteredBookings = new ArrayList<>();
        for(Pair<Booking, BookingId> pair : entries) {
            if(locations.isEmpty() || locations.contains(pair.first.getLocation())){
                filteredBookings.add(pair);
            }
        }
        System.out.println("location");
        return filteredBookings;
    }
}
