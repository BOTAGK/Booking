package GUI;

import Booking.*;
import FilterStrategy.FilterManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.*;

import javax.swing.*;

import java.util.List;

public class MainMenuPanel extends JPanel {

    private DefaultListModel<String> listModel;
    private JList<String> offersList;
    JPanel filtersPanel = new JPanel();
    JLabel priceLabel = new JLabel("        Price       ");
    JTextField minPriceField = new JTextField("Min price");
    JLabel minPriceLabel = new JLabel("Min price");
    JTextField maxPriceField = new JTextField("Max price");
    JLabel maxPriceLabel = new JLabel("Max price");

    JLabel locationLabel = new JLabel("        Location      ");
    JButton locationButton = new JButton("Select Locations");
    Set<String> locationsSet = new HashSet<>();
    String[] locations;

    JLabel minRatingLabel = new JLabel("        Rating      ");
    JTextField minRatingField = new JTextField("Min rating");

    JButton eventTypeFilterButton;
    JButton carTypeFilterButton;



    public MainMenuPanel(JFrame parentFrame) {
        setLayout(new BorderLayout());

//        kategorie(górny panel)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton carRentButton = new JButton("Car Rent");
        carRentButton.addActionListener(new CarRentButtonListener());

        JButton apartmentRentButton = new JButton("Apartment Rent");
        apartmentRentButton.addActionListener(new ApartmentRentButtonListener());

        JButton eventTicketsButton = new JButton("Event Tickets");
        eventTicketsButton.addActionListener(new EventTicketsButtonListener());

        topPanel.add(carRentButton);
        topPanel.add(apartmentRentButton);
        topPanel.add(eventTicketsButton);
        add(topPanel, BorderLayout.NORTH);

//        lewy panel, filtry i użytkownik
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());


        filtersPanel.setLayout(new BoxLayout(filtersPanel, BoxLayout.Y_AXIS));


        filtersPanel.add(priceLabel);
        filtersPanel.add(minPriceLabel);
        filtersPanel.add(minPriceField);
        filtersPanel.add(maxPriceLabel);
        filtersPanel.add(maxPriceField);
        filtersPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        filtersPanel.add(locationLabel);
        filtersPanel.add(locationButton);
        filtersPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        locationButton.addActionListener(new LocationsActionListener());
        minPriceField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                minPriceField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                String minPriceInput;
                minPriceInput = minPriceField.getText();
                System.out.println(minPriceInput);
            }
        });
        maxPriceField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                maxPriceField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                String maxPriceInput;
                maxPriceInput=maxPriceField.getText();
                System.out.println(maxPriceInput);
            }
        });

        minRatingField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                minRatingField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                String minRatingInput;
                minRatingInput=minRatingField.getText();
                System.out.println(minRatingInput);
            }
        });


        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("        User       ");
        JMenuItem item1=new JMenuItem("Show your reservations");
        JMenuItem item2=new JMenuItem("Log out");

        item2.addActionListener(_-> {
           parentFrame.dispose();
           new LoginGui();
        });

        menu.add(item1);
        menu.add(item2);
        menuBar.add(menu);


        leftPanel.add(menuBar, BorderLayout.SOUTH);
        leftPanel.add(filtersPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);

//      centrum
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        List<ApartmentBooking> apartmentRentals = ApartmentBooking.getApartmentsFromFile("ApartmentBookingData.txt");
        for (ApartmentBooking booking : apartmentRentals) {
            listModel.addElement(booking.toString());
        }

        List<CarRentalBooking> carRentals = CarRentalBooking.getCarRentalsFromFile("CarRentalBookingData.txt");
        for (CarRentalBooking carRental : carRentals) {
            listModel.addElement(carRental.toString());
        }

        List<EventTicketBooking> eventTickets = EventTicketBooking.getEventTicketsFromFile("EventTicketBookingData.txt");
        for (EventTicketBooking eventTicket : eventTickets) {
            listModel.addElement(eventTicket.toString());
        }

        JList<String> offersList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(offersList);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

       add(centerPanel, BorderLayout.CENTER);
       add(leftPanel, BorderLayout.WEST);
       add(topPanel, BorderLayout.NORTH);
        setVisible(true);


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

    private void updateButtonText(JButton button, String text) {
        if (text.length() > 16) {
            text = text.substring(0, 16) + "...";
        }
        button.setText(text);
    }




    private class CarRentButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            listModel.clear();
            List <CarRentalBooking> carRentals = CarRentalBooking.getCarRentalsFromFile("CarRentalBookingData.txt");
            for (CarRentalBooking carRental: carRentals) {
                listModel.addElement(carRental.toString());
            }
            ArrayList<String> filters = (ArrayList<String>) FilterManager.getFiltersForCategory("Car Rent");

            filtersPanel.removeAll();

            filtersPanel.add(priceLabel);
            filtersPanel.add(minPriceLabel);
            filtersPanel.add(minPriceField);
            filtersPanel.add(maxPriceLabel);
            filtersPanel.add(maxPriceField);
            filtersPanel.add(Box.createRigidArea(new Dimension(0, 30)));
            filtersPanel.add(locationLabel);
            filtersPanel.add(locationButton);
            filtersPanel.add(Box.createRigidArea(new Dimension(0, 30)));


            JLabel carTypesLabel = new JLabel("Car Type");
            carTypeFilterButton = new JButton("Choose car type");

            filtersPanel.add(carTypesLabel);
            filtersPanel.add(carTypeFilterButton);
            filtersPanel.revalidate();
            filtersPanel.repaint();

            carTypeFilterButton.addActionListener(new CarTypeFilterActionListener());
        }
    }

    private class ApartmentRentButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            listModel.clear();
            List<ApartmentBooking> apartmentRentals = ApartmentBooking.getApartmentsFromFile("ApartmentBookingData.txt");
            for (ApartmentBooking booking : apartmentRentals) {
                listModel.addElement(booking.toString());
            }
            filtersPanel.removeAll();

            filtersPanel.add(priceLabel);
            filtersPanel.add(minPriceLabel);
            filtersPanel.add(minPriceField);
            filtersPanel.add(maxPriceLabel);
            filtersPanel.add(maxPriceField);
            filtersPanel.add(Box.createRigidArea(new Dimension(0, 30)));
            filtersPanel.add(locationLabel);
            filtersPanel.add(locationButton);
            filtersPanel.add(Box.createRigidArea(new Dimension(0, 30)));
            filtersPanel.add(minRatingLabel);
            filtersPanel.add(minRatingField);
            filtersPanel.add(Box.createRigidArea(new Dimension(0, 30)));

            JLabel roomLabel = new JLabel("Room Count");
            JTextField minRoomCountField = new JTextField("Min room count");


            filtersPanel.add(roomLabel);
            filtersPanel.add(minRoomCountField);
            filtersPanel.add(Box.createRigidArea(new Dimension(0, 30)));
            filtersPanel.revalidate();
            filtersPanel.repaint();
        }
    }

    private class EventTicketsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            listModel.clear();
            List<EventTicketBooking> eventTickets = EventTicketBooking.getEventTicketsFromFile("EventTicketBookingData.txt");
            for (EventTicketBooking booking : eventTickets) {
                listModel.addElement(booking.toString());
            }
            ArrayList<String> filters = (ArrayList<String>) FilterManager.getFiltersForCategory("Event Ticket");

            filtersPanel.removeAll();

            filtersPanel.add(priceLabel);
            filtersPanel.add(minPriceLabel);
            filtersPanel.add(minPriceField);
            filtersPanel.add(maxPriceLabel);
            filtersPanel.add(maxPriceField);
            filtersPanel.add(Box.createRigidArea(new Dimension(0, 30)));
            filtersPanel.add(locationLabel);
            filtersPanel.add(locationButton);
            filtersPanel.add(Box.createRigidArea(new Dimension(0, 30)));


            JLabel eventTypesLabel = new JLabel("Event Type");
            eventTypeFilterButton = new JButton("Choose event type");
            eventTypeFilterButton.addActionListener(new EventFilterActionListener());

            filtersPanel.add(eventTypesLabel);
            filtersPanel.add(eventTypeFilterButton);
            filtersPanel.revalidate();
            filtersPanel.repaint();
        }
    }



    private class LocationsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            JCheckBox[] checkBoxes = new JCheckBox[locations.length];
            for (int i = 0; i < locations.length; i++) {
                checkBoxes[i] = new JCheckBox(locations[i]);
                panel.add(checkBoxes[i]);
            }
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setPreferredSize(new Dimension(200, 150));
            int result = JOptionPane.showConfirmDialog(null, scrollPane, "Select Locations", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                StringBuilder selectedLocations = new StringBuilder();
                for (JCheckBox checkBox : checkBoxes) {
                    if (checkBox.isSelected()) {
                        if (!selectedLocations.isEmpty()) {
                            selectedLocations.append(", ");
                        }
                        selectedLocations.append(checkBox.getText());
                    }
                }
                if (selectedLocations.isEmpty()) {
                    locationButton.setText("Select Locations");
                } else {
                    updateButtonText(locationButton, selectedLocations.toString());
                    System.out.println(selectedLocations);
                }
            }
        }
    }

    private class EventFilterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<String> eventTypes = EventTicketBooking.getEventTypesFromFile();
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            JCheckBox[] checkBoxes = new JCheckBox[eventTypes.size()];
            for (int i = 0; i < eventTypes.size(); i++) {
                checkBoxes[i] = new JCheckBox(eventTypes.get(i));
                panel.add(checkBoxes[i]);
            }
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setPreferredSize(new Dimension(200, 150));
            int result = JOptionPane.showConfirmDialog(null, scrollPane, "Select Event Types", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                StringBuilder selectedEventTypes = new StringBuilder();
                for (JCheckBox checkBox : checkBoxes) {
                    if (checkBox.isSelected()) {
                        if (selectedEventTypes.length() > 0) {
                            selectedEventTypes.append(", ");
                        }
                        selectedEventTypes.append(checkBox.getText());
                    }
                }
                if (selectedEventTypes.length() == 0) {
                    eventTypeFilterButton.setText("Select Event Types");
                } else {
                    updateButtonText(eventTypeFilterButton, selectedEventTypes.toString());
                }
                System.out.println(selectedEventTypes);
            }
        }
    }

    private class CarTypeFilterActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            List<String> carTypes = CarRentalBooking.getCarTypesFromFile();
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            JCheckBox[] checkBoxes = new JCheckBox[carTypes.size()];
            for (int i = 0; i < carTypes.size(); i++) {
                checkBoxes[i] = new JCheckBox(carTypes.get(i));
                panel.add(checkBoxes[i]);
            }
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setPreferredSize(new Dimension(200, 150));
            int result = JOptionPane.showConfirmDialog(null, scrollPane, "Select Car Types", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                StringBuilder selectedCarTypes = new StringBuilder();
                for (JCheckBox checkBox : checkBoxes) {
                    if (checkBox.isSelected()) {
                        if (selectedCarTypes.length() > 0) {
                            selectedCarTypes.append(", ");
                        }
                        selectedCarTypes.append(checkBox.getText());
                    }
                }
                if (selectedCarTypes.length() == 0) {
                    carTypeFilterButton.setText("Select Car Types");
                } else {
                    updateButtonText(carTypeFilterButton, selectedCarTypes.toString());
                }
                System.out.println(selectedCarTypes);
            }
        }
    }
}
