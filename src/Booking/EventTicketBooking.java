package Booking;

import Main.User;

import java.time.LocalDate;
import java.util.ArrayList;

public class EventTicketBooking extends Booking {
    String eventType;
    String artistOrTeam;
    int availableTickets;
    LocalDate date;
    ArrayList<User> users;

    public EventTicketBooking(String id, String name, String location, double price, String startDate, String endDate, String eventType, String artistOrTeam, int availableTickets, LocalDate date) {
        super(id, name, location, price, startDate, endDate);
        this.eventType = eventType;
        this.artistOrTeam = artistOrTeam;
        this.availableTickets = availableTickets;
        this.date = date;
        this.users = new ArrayList<>();
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

    public Boolean isFree() {
        if(availableTickets > 0) return true;
        return false;
    }
    public void Book(User user) {
        availableTickets--;
        users.add(user);
    }
    public void cancelBooking(User user) {
        availableTickets++;
        users.remove(user);
    }
}
