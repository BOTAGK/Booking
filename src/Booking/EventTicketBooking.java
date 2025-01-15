package Booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventTicketBooking extends Booking {
    private String eventType;
    private String artistOrTeam;
    private int availableTickets;
    
    public EventTicketBooking() {}

    public EventTicketBooking(String name, String location, double price, LocalDate startDate, LocalDate endDate, String eventType, String artistOrTeam, int availableTickets) {
        super(name, location, price, startDate, endDate);
        this.eventType = eventType;
        this.artistOrTeam = artistOrTeam;
        this.availableTickets = availableTickets;
    }
    public String getEventType() {
        return eventType;
    }
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    public String getArtistOrTeam() {
        return artistOrTeam;
    }
    public void setArtistOrTeam(String artistOrTeam) {
        this.artistOrTeam = artistOrTeam;
    }

    public boolean getAvailable() {
        if(availableTickets > 0) return true;
        else return false;
    }
    public void setAvailable(Boolean available) {
        if(available) availableTickets++;
        else availableTickets--;
    }

    public static List<EventTicketBooking> getEventTicketsFromFile(String fileName) {
        List<EventTicketBooking> bookings = new ArrayList<>();
        List<String[]> data = Booking.loadDataFromFile(fileName);
        for (String[] part : data) {
            bookings.add(new EventTicketBooking(
                    part[1], part[2], Double.parseDouble(part[3]), LocalDate.parse(part[4]), null,
                    part[5], part[6], Integer.parseInt(part[7])
            ));
        }
        return bookings;
    }

    @Override
    public String getIdType() {
        return "EVT";
    }

    @Override
    public String toString() {
        return "Artist/Team: " + artistOrTeam + '\n' +
                "Event Type: " + eventType + '\n' +
                "Available tickets: " + availableTickets + '\n'+
                super.toString();
    }
}
