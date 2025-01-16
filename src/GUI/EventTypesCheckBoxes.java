package GUI;

import Booking.EventTicketBooking;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EventTypesCheckBoxes {
    Set<String> eventTypesSet = new HashSet<>();
    String[] eventTypes;

    public EventTypesCheckBoxes() {
        List<EventTicketBooking> eventTickets = EventTicketBooking.getEventTicketsFromFile("EventTicketBookingData.txt");
        for (EventTicketBooking eventTicket : eventTickets) {
            eventTypesSet.add(eventTicket.getEventType());
        }
        eventTypes = eventTypesSet.toArray(new String[0]);
    }

    public JPanel createEventTypesPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (String eventType : eventTypes) {
            JCheckBox checkBox = new JCheckBox(eventType);
            panel.add(checkBox);
        }
        return panel;
    }

    public void updateButtonWithSelectedEventTypes(JButton button) {
        JPanel panel = createEventTypesPanel();
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(200, 150));
        int result = JOptionPane.showConfirmDialog(null, scrollPane, "Select Event Types", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            StringBuilder selectedEventTypesSB = new StringBuilder();
            for (Component component : panel.getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) component;
                    if (checkBox.isSelected()) {
                        if (selectedEventTypesSB.length() > 0) {
                            selectedEventTypesSB.append(", ");
                        }
                        selectedEventTypesSB.append(checkBox.getText());
                    }
                }
            }
            if (selectedEventTypesSB.length() == 0) {
                button.setText("Select event type");
            } else {
                button.setText(selectedEventTypesSB.toString());
            }
        }
    }
}