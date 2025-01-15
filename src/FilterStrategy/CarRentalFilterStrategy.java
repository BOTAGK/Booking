package FilterStrategy;

import Booking.ApartmentBooking;
import Booking.Booking;
import Booking.CarRentalBooking;

import java.util.List;
import java.util.stream.Collectors;

public class CarRentalFilterStrategy implements FilterStrategy {
    private List<String> carTypes;

    public CarRentalFilterStrategy() {}

    public CarRentalFilterStrategy(List<String> carTypes) {
        this.carTypes = carTypes;
    }

    @Override
    public List<Booking> filter(List<Booking> bookings) {
        return bookings.stream()
                .filter(booking -> booking.getIdType().equals(new CarRentalBooking().getIdType()))
                .filter(booking -> carTypes==null || carTypes.contains(((CarRentalBooking) booking).getCarType()))
                .collect(Collectors.toList());
    }
}
