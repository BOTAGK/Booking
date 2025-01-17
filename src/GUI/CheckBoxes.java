package GUI;

import Booking.ApartmentBooking;
import Booking.CarRentalBooking;
import Booking.EventTicketBooking;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckBoxes {
    Set<String> locationsSet = new HashSet<>();
    String[] locations;

    public CheckBoxes() {
        List<ApartmentBooking> apartmentRentalsLocations = ApartmentBooking.getApartmentsFromFile("ApartmentBookingData.txt");
        for (ApartmentBooking booking : apartmentRentalsLocations) {
            locationsSet.add(booking.getLocation());
        }

        List<CarRentalBooking> carRentalsLocations = CarRentalBooking.getCarRentalsFromFile("CarRentalBookingData.txt");
        for (CarRentalBooking carRental : carRentalsLocations) {
            locationsSet.add(carRental.getLocation());
        }

        List<EventTicketBooking> eventTicketsLocations = EventTicketBooking.getEventTicketsFromFile("EventTicketBookingData.txt");
        for (EventTicketBooking eventTicket : eventTicketsLocations) {
            locationsSet.add(eventTicket.getLocation());
        }

        locations = locationsSet.toArray(new String[0]);
    }

    public JPanel createLocationsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (String location : locations) {
            JCheckBox checkBox = new JCheckBox(location);
            panel.add(checkBox);
        }
        return panel;
    }



}
