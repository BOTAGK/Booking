package GUI;

import javax.swing.*;

import Booking.Booking;
import BookingService.BookingService;
import BookingService.FilterStrategy.ApartmentFilterStrategy;
import BookingService.FilterStrategy.CarRentalFilterStrategy;
import BookingService.FilterStrategy.EventTicketFilterStrategy;
import BookingService.FilterStrategy.FilterStrategy;
import BookingService.FilterStrategy.LocationFilterStrategy;
import BookingService.FilterStrategy.PriceFilterStrategy;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FiltersDialog extends JDialog {
    public enum BookingCategory {
        All,
        Apartments,
        Cars,
        Events
    }

    private JLabel minPriceLabel = new JLabel("Min price");
    private JTextField minPriceField = new JTextField();
    private JLabel maxPriceLabel = new JLabel("Max price");
    private JTextField maxPriceField = new JTextField();
    private JLabel locationLabel = new JLabel("Location");
    private JButton locationButton = new JButton("Select location");
    private JButton applyFiltersButton = new JButton("Apply filters");
    private JButton carTypeFilterButton;
    private JLabel minRatingLabel = new JLabel("Min rating");
    private JTextField minRatingField = new JTextField();
    private JButton eventTypeFilterButton;
    private JTextField minRoomCountField = new JTextField();

    //  do filtorwania
    private int maxPrice;
    private int minPrice;
    private int minRating;
    private int minRoomCount;
    private List<String> selectedLocations = new ArrayList<>();
    private List<String> selectedCarTypes = new ArrayList<>();
    private List<String> selectedEventTypes = new ArrayList<>();

    private DefaultListModel<Booking> listModel;

    public FiltersDialog(BookingCategory bookingCategory, DefaultListModel<Booking> listModel) {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Filters"));
        add(applyFiltersButton);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(minPriceLabel);
        minPriceField.setMaximumSize(new Dimension(200, 20));
        add(minPriceField);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(maxPriceLabel);
        maxPriceField.setMaximumSize(new Dimension(200, 20));
        add(maxPriceField);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(locationLabel);
        add(locationButton);
        add(Box.createRigidArea(new Dimension(0, 30)));

        this.listModel = listModel;

        // action listenery dla ogólnych filtrów

        minPriceField.addActionListener(e -> {
            try {
                minPrice = Integer.parseInt(minPriceField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number (Min price)");
            }
        });

        maxPriceField.addActionListener(e -> {
            try {
                maxPrice = Integer.parseInt(maxPriceField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number (Max price)");
            }
        });

        locationButton.addActionListener(e -> openLocationCheckBox());

        applyFiltersButton.addActionListener(_ -> { applyFilters(bookingCategory); });


        if (bookingCategory == BookingCategory.Cars) {
            addCarTypeFilters();
        } else if (bookingCategory == BookingCategory.Apartments) {
            addRoomAndRatingFilters();
        } else if (bookingCategory == BookingCategory.Events) {
            addEventTypeFilters();
        }

        pack();
        setLocationRelativeTo(null);
    }

    private void applyFilters(BookingCategory bookingCategory) {
        List<FilterStrategy> filters = new ArrayList<>();

        filters.add(new PriceFilterStrategy(minPrice, maxPrice));
        filters.add(new LocationFilterStrategy(selectedLocations));

        switch(bookingCategory)
        {
            case BookingCategory.Cars:
                filters.add(new CarRentalFilterStrategy(selectedCarTypes)); break;
            case BookingCategory.Apartments:
                filters.add(new ApartmentFilterStrategy(minRoomCount, minRating)); break;
            case BookingCategory.Events:
                filters.add(new EventTicketFilterStrategy(selectedEventTypes)); break;
            default:
                break;
        }

        List<Booking> filteredBookings = BookingService.getInstance().filterBookings(filters); 

        // Update the listModel reference
        listModel.clear();
        for(Booking booking : filteredBookings) {
            listModel.addElement(booking);
        }
    }

    private void addCarTypeFilters() {

        JLabel carTypesLabel = new JLabel("Car Type");
        carTypeFilterButton = new JButton("Choose car type");
        add(carTypesLabel);
        add(carTypeFilterButton);

        carTypeFilterButton.addActionListener(e -> openCarTypeCheckBox());
    }

    private void addRoomAndRatingFilters() {
        JLabel roomLabel = new JLabel("Room Count");

        add(roomLabel);
        add(minRoomCountField);
        minRoomCountField.setMaximumSize(new Dimension(200, 20));
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(minRatingLabel);
        add(minRatingField);
        minRatingField.setMaximumSize(new Dimension(200, 20));

        minRoomCountField.addActionListener(e -> {
            try {
                minRoomCount = Integer.parseInt(minRoomCountField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number (Room count)");
            }
        });

        minRatingField.addActionListener(e -> {
            try {
                minRating = Integer.parseInt(minRatingField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number (Min rating)");
            }
        });
    }

    private void addEventTypeFilters() {
        JLabel eventTypesLabel = new JLabel("Event Type");
        eventTypeFilterButton = new JButton("Choose event type");
        add(eventTypesLabel);
        add(eventTypeFilterButton);

        eventTypeFilterButton.addActionListener(e -> openEventTypeCheckBox());
    }


    private void openLocationCheckBox() {
        CheckBoxes checkBoxes = new CheckBoxes();
        JPanel locationCheckBox = checkBoxes.createLocationsPanel();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(locationCheckBox);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(200, 150));
        int result = JOptionPane.showConfirmDialog(null, scrollPane, "Select Locations", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            selectedLocations.clear();
            StringBuilder selectedLocationsSB = new StringBuilder();
            for (Component component : locationCheckBox.getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) component;
                    if (checkBox.isSelected()) {
                        if (selectedLocationsSB.length() > 0) {
                            selectedLocationsSB.append(", ");
                        }
                        selectedLocationsSB.append(checkBox.getText());
                        selectedLocations.add(checkBox.getText());
                    }
                }
            }
            if (selectedLocationsSB.length() == 0) {
                locationButton.setText("Select location");
            } else {
                locationButton.setText(selectedLocationsSB.toString());
            }
        }
    }

    private void openEventTypeCheckBox() {
        EventTypesCheckBoxes eventTypesCheckBoxes = new EventTypesCheckBoxes();
        JPanel eventTypeCheckBox = eventTypesCheckBoxes.createEventTypesPanel();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(eventTypeCheckBox);
<<<<<<< Updated upstream

=======
    
>>>>>>> Stashed changes
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(200, 150));
        int result = JOptionPane.showConfirmDialog(null, scrollPane, "Select Event Types", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            selectedEventTypes.clear();
            StringBuilder selectedEventTypesSB = new StringBuilder();
            for (Component component : eventTypeCheckBox.getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) component;
                    if (checkBox.isSelected()) {
                        if (selectedEventTypesSB.length() > 0) {
                            selectedEventTypesSB.append(", ");
                        }
                        selectedEventTypesSB.append(checkBox.getText());
                        selectedEventTypes.add(checkBox.getText());
                    }
                }
            }
            if (selectedEventTypesSB.length() == 0) {
                eventTypeFilterButton.setText("Select event type");
            } else {
                eventTypeFilterButton.setText(selectedEventTypesSB.toString());
            }
        }
    }

    private void openCarTypeCheckBox() {
        CarTypesCheckBoxes carTypesCheckBoxes = new CarTypesCheckBoxes();
        JPanel carTypeCheckBox = carTypesCheckBoxes.createCarTypesPanel();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(carTypeCheckBox);
<<<<<<< Updated upstream

=======
    
>>>>>>> Stashed changes
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(200, 150));
        int result = JOptionPane.showConfirmDialog(null, scrollPane, "Select Car Types", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            selectedCarTypes.clear();
            StringBuilder selectedCarTypesSB = new StringBuilder();
            for (Component component : carTypeCheckBox.getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) component;
                    if (checkBox.isSelected()) {
                        if (selectedCarTypesSB.length() > 0) {
                            selectedCarTypesSB.append(", ");
                        }
                        selectedCarTypesSB.append(checkBox.getText());
                        selectedCarTypes.add(checkBox.getText());
                    }
                }
            }
            if (selectedCarTypesSB.length() == 0) {
                carTypeFilterButton.setText("Select car type");
            } else {
                carTypeFilterButton.setText(selectedCarTypesSB.toString());
            }
        }
    }
}