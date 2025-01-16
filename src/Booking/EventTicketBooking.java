package Booking;

import java.time.LocalDate;

import BookingService.BookingId;

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

    @Override
    public BookingId.Prefix getIdPrefix() {
        return BookingId.Prefix.EVT;
    }

    @Override
    public String toString() {
        return "Artist/Team: " + artistOrTeam + '\n' +
                "Event Type: " + eventType + '\n' +
                "Available tickets: " + availableTickets + '\n'+
                super.toString();
    }
}
