package Booking;

import java.time.LocalDate;

public class EventTicketBooking extends Booking {
    private String eventType;
    private String artistOrTeam;
    private int availableTickets;
    public EventTicketBooking(String id, String name, String location, double price, LocalDate startDate, LocalDate endDate, String eventType, String artistOrTeam, int availableTickets) {
        super(id, name, location, price, startDate, endDate);
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
    public int getAvailableTickets() {
        return availableTickets;
    }
    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }
    /*public boolean reserveTickets(){
        //tu bedzie rezerwowanie (nw czy ma byc boolean)
    }
    public boolean cancelBooking(){
        //tutaj cancelowanie
    }*/
}
