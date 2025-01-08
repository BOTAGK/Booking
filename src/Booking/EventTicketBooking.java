package Booking;

import java.time.LocalDate;

public class EventTicketBooking extends Booking {
    String eventType;
    String artistOrTeam;
    int availableTickets;
    LocalDate date; //usunalbym date jesli mamy StartDate i EndDate
    public EventTicketBooking(String id, String name, String location, double price, LocalDate startDate, LocalDate endDate, String eventType, String artistOrTeam, int availableTickets, LocalDate date) {
        super(id, name, location, price, startDate, endDate);
        this.eventType = eventType;
        this.artistOrTeam = artistOrTeam;
        this.availableTickets = availableTickets;
        this.date = date;
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
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    /*public boolean reserveTickets(){
        //tu bedzie rezerwowanie (nw czy ma byc boolean)
    }
    public boolean cancelBooking(){
        //tutaj cancelowanie
    }*/
}
