package GUI;

import Booking.CarRentalBooking;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarTypesCheckBoxes {
    Set<String> carTypesSet = new HashSet<>();
    String[] carTypes;

    public CarTypesCheckBoxes() {
        List<CarRentalBooking> carRentals = CarRentalBooking.getCarRentalsFromFile("CarRentalBookingData.txt");
        for (CarRentalBooking carRental : carRentals) {
            carTypesSet.add(carRental.getCarType());
        }
        carTypes = carTypesSet.toArray(new String[0]);
    }

    public JPanel createCarTypesPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (String carType : carTypes) {
            JCheckBox checkBox = new JCheckBox(carType);
            panel.add(checkBox);
        }
        return panel;
    }

    public void updateButtonWithSelectedCarTypes(JButton button) {
        JPanel panel = createCarTypesPanel();
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(200, 150));
        int result = JOptionPane.showConfirmDialog(null, scrollPane, "Select Car Types", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            StringBuilder selectedCarTypesSB = new StringBuilder();
            for (Component component : panel.getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) component;
                    if (checkBox.isSelected()) {
                        if (selectedCarTypesSB.length() > 0) {
                            selectedCarTypesSB.append(", ");
                        }
                        selectedCarTypesSB.append(checkBox.getText());
                    }
                }
            }
            if (selectedCarTypesSB.length() == 0) {
                button.setText("Select car type");
            } else {
                button.setText(selectedCarTypesSB.toString());
            }
        }
    }
}