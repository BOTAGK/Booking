package Booking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import BookingService.BookingId;

import javax.swing.*;

public class EventTicketBooking extends Booking {
    private String eventType;
    private String artistOrTeam;
    private int availableTickets;
    private String path;
    private ImageIcon icon=null;
    public EventTicketBooking() {}

    public EventTicketBooking(String name, String location, double price, LocalDate startDate, LocalDate endDate, String eventType, String artistOrTeam, int availableTickets, String path) {
        super(name, location, price, startDate, endDate);
        this.path=path;
        this.icon=new ImageIcon(path);
        this.eventType = eventType;
        this.artistOrTeam = artistOrTeam;
        this.availableTickets = availableTickets;
    }
    public ImageIcon getIcon() {
        return icon;
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
        for (String[] parts : data) {
            bookings.add(new EventTicketBooking(
                    parts[0], parts[1], Double.parseDouble(parts[2]), LocalDate.parse(parts[3]), null,
                    parts[4], parts[5], Integer.parseInt(parts[6]),parts[7]
            ));
        }
        return bookings;
    }
    public static List<String> getEventTypesFromFile() {
        Set<String> eventTypes = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader("EventTicketBookingData.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 4) {
                    eventTypes.add(parts[4].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(eventTypes);
    }

    @Override
    public BookingId.Prefix getIdPrefix() {
        return BookingId.Prefix.EVT;
    }

    @Override
    public String toString() {
        return artistOrTeam + ", " +
                eventType + ", " + getPrice() + " $, Available Tickets: " + availableTickets + ", " + getName() + ", "+
                getLocation() + ", " + getPrice() + "$ , Date: " + getStartDate().toString();
    }
}
