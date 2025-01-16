package BookingService.FilterStrategy;

import Booking.EventTicketBooking;
import BookingService.BookingId;
import Util.Pair;
import Booking.Booking;

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
        return entries.stream()
            .filter(entry -> entry.second.matchesType(new BookingId(BookingId.Prefix.EVT)))
            .filter(entry -> eventTypes==null || eventTypes.contains(((EventTicketBooking)entry.first).getEventType()))
            .collect(Collectors.toList());
    }
}
