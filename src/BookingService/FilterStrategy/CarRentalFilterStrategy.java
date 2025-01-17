package BookingService.FilterStrategy;

import Booking.Booking;
import Booking.CarRentalBooking;
import BookingService.BookingId;
import Util.Pair;

import java.util.ArrayList;
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
        List<Pair<Booking, BookingId>> filteredBookings= new ArrayList<>();
        for(Pair<Booking, BookingId> pair: entries) {
            if(pair.first instanceof CarRentalBooking) {
                CarRentalBooking carRentalBooking = (CarRentalBooking) pair.first;
                if(carTypes.isEmpty() || carTypes.contains(carRentalBooking.getCarType())) {
                    filteredBookings.add(pair);

                }
            }
        }
        return filteredBookings;
    }
}
