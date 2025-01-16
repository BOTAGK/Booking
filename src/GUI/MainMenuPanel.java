package GUI;

import javax.swing.*;

import BookingService.User;
import BookingService.FilterStrategy.FilterManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainMenuPanel extends JPanel {

    private DefaultListModel<String> listModel;
    private JList<String> offersList;
    private FilterManager filterManager = new FilterManager();

    public MainMenuPanel(JFrame parentFrame, User user) {
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

        JPanel filtersPanel = new JPanel();
        filtersPanel.setLayout(new BoxLayout(filtersPanel, BoxLayout.Y_AXIS));

        JLabel priceLabel = new JLabel("        Price       ");
        filtersPanel.add(priceLabel);

        JTextField minPriceField = new JTextField("Min price");
        JLabel minPriceLabel = new JLabel("Min price");
        filtersPanel.add(minPriceLabel);
        filtersPanel.add(minPriceField);

        JTextField maxPriceField = new JTextField("Max price");
        JLabel maxPriceLabel = new JLabel("Max price");
        filtersPanel.add(maxPriceLabel);
        filtersPanel.add(maxPriceField);

        filtersPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JLabel locationLabel = new JLabel("        Location      ");
        filtersPanel.add(locationLabel);
        JTextField locationField = new JTextField("Location");
        filtersPanel.add(locationField);

        filtersPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JLabel minRatingLabel = new JLabel("        Minimal Rating      ");
        filtersPanel.add(minRatingLabel);
        JTextField minRatingField = new JTextField("Min rating");
        filtersPanel.add(minRatingField);

        filtersPanel.add(Box.createRigidArea(new Dimension(0, 30)));


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
        locationField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                locationField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                String locationInput;
                locationInput=locationField.getText();
                System.out.println(locationInput);
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
        loadOffersFromFile("ApartmentBookingData.txt", listModel);
        loadOffersFromFile("CarRentalBookingData.txt", listModel);
        loadOffersFromFile("EventTicketBookingData.txt", listModel);

        JList<String> offersList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(offersList);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

       add(centerPanel, BorderLayout.CENTER);
       add(leftPanel, BorderLayout.WEST);
       add(topPanel, BorderLayout.NORTH);
        setVisible(true);



    }

    private void loadOffersFromFile(String fileName, DefaultListModel<String> listModel) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String formattedLine = formatOffer(line);
                if (formattedLine != null) {
                    listModel.addElement(formattedLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatOffer(String line) {
        String[] parts = line.split(",");
        if (parts.length == 0) {
            return null;
        }
        String type = parts[0].substring(0, 1);
        switch (type) {
            case "A":
                return String.format("Apartment: %s - %s - €%s/day (%s to %s)",
                        parts[1], parts[2], parts[3], parts[4], parts[5]);
            case "C":
                return String.format("Car: %s - %s - %s - €%s/day (%s to %s)",
                        parts[7], parts[2], parts[6], parts[3], parts[4], parts[5]);
            case "E":
                return String.format("Event: %s - %s - €%s - %s (%s)",
                        parts[1], parts[2], parts[3], parts[6], parts[5]);
            default:
                return "Unknown offer type";
        }
    }
    private class CarRentButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            listModel.clear();
            loadOffersFromFile("CarRentalBookingData.txt", listModel);

            ArrayList<String> filters = (ArrayList<String>) FilterManager.getFiltersForCategory("Car Rent");
        }
    }

    private class ApartmentRentButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            listModel.clear();
            loadOffersFromFile("ApartmentBookingData.txt", listModel);

            ArrayList<String> filters = (ArrayList<String>) FilterManager.getFiltersForCategory("Apartment Rent");
        }
    }

    private class EventTicketsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            listModel.clear();
            loadOffersFromFile("EventTicketBookingData.txt", listModel);

            ArrayList<String> filters = (ArrayList<String>) FilterManager.getFiltersForCategory("Event Ticket");
        }
    }
}