package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FiltersDialog extends JDialog {
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

    public FiltersDialog(int bookingTypeID) {
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


        if (bookingTypeID == 1) {
            addCarTypeFilters();
        } else if (bookingTypeID == 2) {
            addRoomAndRatingFilters();
        } else if (bookingTypeID == 3) {
            addEventTypeFilters();
        }

        pack();
        setLocationRelativeTo(null);
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
        eventTypesCheckBoxes.updateButtonWithSelectedEventTypes(eventTypeFilterButton);
    }

    private void openCarTypeCheckBox() {
        CarTypesCheckBoxes carTypesCheckBoxes = new CarTypesCheckBoxes();
        carTypesCheckBoxes.updateButtonWithSelectedCarTypes(carTypeFilterButton);
    }
}