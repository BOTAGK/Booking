package FilterStrategy;

import Booking.EventTicketBooking;
import Booking.Booking;

import java.util.List;
import java.util.stream.Collectors;

public class EventTicketFilterStrategy implements FilterStrategy {
    private List<String> eventTypes;

    public EventTicketFilterStrategy(List<String> eventTypes) {
        this.eventTypes = eventTypes;
    }

    @Override
    public List<Booking> filter(List<Booking> bookings) {
        return bookings.stream()
                .filter(booking -> booking.getIdType().equals(new EventTicketBooking().getIdType()))
                .filter(booking -> eventTypes==null || eventTypes.contains(((EventTicketBooking) booking).getEventType()))
                .collect(Collectors.toList());
    }
}
