package BookingService.FilterStrategy;

import Booking.EventTicketBooking;
import BookingService.BookingId;
import Util.Pair;
import Booking.Booking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventTicketFilterStrategy implements FilterStrategy {
    private List<String> eventTypes;

    public EventTicketFilterStrategy() {}

    public EventTicketFilterStrategy(List<String> eventTypes) {
        this.eventTypes = eventTypes;
    }

    @Override
    public List<Pair<Booking, BookingId>> filter(List<Pair<Booking, BookingId>> entries) {
        List<Pair<Booking, BookingId>> filteredBookings= new ArrayList<>();
        for(Pair<Booking, BookingId> pair: entries) {
            if(pair.first instanceof EventTicketBooking) {
                EventTicketBooking eventTicketBooking = (EventTicketBooking) pair.first;
                if(eventTypes.isEmpty()  || eventTypes.contains(eventTicketBooking.getEventType())) {
                    filteredBookings.add(pair);
                }
            }
        }
        return filteredBookings;
    }
}
