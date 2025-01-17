package GUI;

import Booking.*;
import BookingService.BookingId;
import BookingService.BookingService;
import BookingService.BookingService;
import BookingService.User;
import BookingService.FilterStrategy.ApartmentFilterStrategy;
import BookingService.FilterStrategy.CarRentalFilterStrategy;
import BookingService.FilterStrategy.EventTicketFilterStrategy;
import BookingService.FilterStrategy.FilterStrategy;
import BookingService.FilterStrategy.LocationFilterStrategy;
import BookingService.FilterStrategy.PriceFilterStrategy;
import BookingService.ResourceManager;
import BookingService.ResourceManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class MainMenuPanel extends JPanel {

    private DefaultListModel<Booking> listModel;
    JPanel filtersPanel = new JPanel();
    JLabel priceLabel = new JLabel("        Price       ");
    JTextField minPriceField = new JTextField("0");
    JLabel minPriceLabel = new JLabel("Min price");
    JTextField maxPriceField = new JTextField("1000");
    JLabel maxPriceLabel = new JLabel("Max price");

    JLabel locationLabel = new JLabel("        Location      ");
    JButton locationButton = new JButton("Select Locations");
    Set<String> locationsSet = new HashSet<>();
    String[] locations;

    JLabel minRatingLabel = new JLabel("        Minimal Rating      ");
    JTextField minRatingField = new JTextField("Min rating");

    JButton eventTypeFilterButton;
    JButton carTypeFilterButton;

    JButton applyFiltersButton = new JButton("Apply filters");

    JList<String> offersList;

    // XD
    private List<String> selectedLocations = new ArrayList<>();
    private int minRoomCount;
    private int maxRoomCount;
    private List<String> selectedCarTypes = new ArrayList<>();
    private List<String> selectedEventTypes = new ArrayList<>();

    private List<FilterStrategy> filters = new ArrayList<>();
        private JFrame parentFrame;
                private User user;
    private int type_lol;
            
            
            
    public MainMenuPanel(JFrame parentFrame, User user) {
        setLayout(new BorderLayout());
        this.parentFrame=parentFrame;
        this.user = user;

        // kategorie(górny panel)
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

        // lewy panel, filtry i użytkownik
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        filtersPanel.setLayout(new BoxLayout(filtersPanel, BoxLayout.Y_AXIS));


        filtersPanel.add(applyFiltersButton);
        filtersPanel.add(Box.createRigidArea(new Dimension(0, 30)));
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
                maxPriceInput = maxPriceField.getText();
                System.out.println(maxPriceInput);
            }
        });
        locationButton.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                String locationInput;
                locationInput=locationButton.getText();
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
                minRatingInput = minRatingField.getText();
                System.out.println(minRatingInput);
            }
        });
        applyFiltersButton.addActionListener((ActionEvent _) -> { applyFilters(); });


        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("        User       ");
        JMenuItem item1 = new JMenuItem("My account");
        JMenuItem item2 = new JMenuItem("Log out");

        item2.addActionListener(_ -> {
            parentFrame.dispose();
            new LoginGui();
        });

        menu.add(item1);
        menu.add(item2);
        menuBar.add(menu);

        item1.addActionListener(_ -> {
            parentFrame.dispose();
            new UserMenuGUI(user);
        });

        leftPanel.add(menuBar, BorderLayout.SOUTH);
        leftPanel.add(filtersPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);

        // centrum
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        List<ApartmentBooking> apartmentRentals = ResourceManager.getInstance().getApartmentBookings();
        List<ApartmentBooking> apartmentRentals = ResourceManager.getInstance().getApartmentBookings();
        for (ApartmentBooking booking : apartmentRentals) {
            BookingService.getInstance().createBooking(booking);
            listModel.addElement(booking);
        }

        List<CarRentalBooking> carRentals = ResourceManager.getInstance().getCarRentalBookings();
        List<CarRentalBooking> carRentals = ResourceManager.getInstance().getCarRentalBookings();
        for (CarRentalBooking carRental : carRentals) {
            BookingService.getInstance().createBooking(carRental);
            listModel.addElement(carRental);
        }

        List<EventTicketBooking> eventTickets = ResourceManager.getInstance().getEventTicketBookings();
        List<EventTicketBooking> eventTickets = ResourceManager.getInstance().getEventTicketBookings();
        for (EventTicketBooking eventTicket : eventTickets) {
            BookingService.getInstance().createBooking(eventTicket);
            listModel.addElement(eventTicket);
        }
        Font font =new Font("Arial MS", Font.PLAIN, 24);
        Font font =new Font("Arial MS", Font.PLAIN, 24);
        JList<Booking> offersList = new JList<>(listModel);
        offersList.setFont(font);
        offersList.setFont(font);
        offersList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Booking) {


                    setText(value.toString());
                    Image image=((Booking) value).getIcon().getImage();
                    image=image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    ImageIcon icon=new ImageIcon(image);
                    setIcon(icon);
                    Image image=((Booking) value).getIcon().getImage();
                    image=image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    ImageIcon icon=new ImageIcon(image);
                    setIcon(icon);
                }
                return this;
            }
        });

        offersList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = offersList.locationToIndex(e.getPoint());
                    Booking selectedBooking = offersList.getModel().getElementAt(index);
                    int result = JOptionPane.showConfirmDialog(null, "Do you want to reserve this booking?\n" + selectedBooking, "Confirm your reservation", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        // Dodaj logikę rezerwacji
                        BookingId bookingId = BookingService.getInstance().getBookingId(selectedBooking);
                        if (bookingId != null) {
                            BookingService.getInstance().bookBooking(user, bookingId);
                            System.out.println("Booking reserved: " + selectedBooking);
                            System.out.println("User's bookings: " + user.getBookingIds());
                        } else {
                            System.out.println("Booking ID is null for selected booking: " + selectedBooking);
                        }
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(offersList);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);
        add(topPanel, BorderLayout.NORTH);
        setVisible(true);

        List<ApartmentBooking> apartmentRentalsLocations = ResourceManager.getInstance().getApartmentBookings();
        for (ApartmentBooking booking : apartmentRentalsLocations) {
            locationsSet.add(booking.getLocation());
        }

        List<CarRentalBooking> carRentalsLocations = ResourceManager.getInstance().getCarRentalBookings();
        for (CarRentalBooking carRental : carRentalsLocations) {
            locationsSet.add(carRental.getLocation());
        }

        List<EventTicketBooking> eventTicketsLocations = ResourceManager.getInstance().getEventTicketBookings();
        for (EventTicketBooking eventTicket : eventTicketsLocations) {
            locationsSet.add(eventTicket.getLocation());
        }

        locations = locationsSet.toArray(new String[0]);
    }
        List<ApartmentBooking> apartmentRentalsLocations = ResourceManager.getInstance().getApartmentBookings();
        for (ApartmentBooking booking : apartmentRentalsLocations) {
            locationsSet.add(booking.getLocation());
        }

        List<CarRentalBooking> carRentalsLocations = ResourceManager.getInstance().getCarRentalBookings();
        for (CarRentalBooking carRental : carRentalsLocations) {
            locationsSet.add(carRental.getLocation());
        }

        List<EventTicketBooking> eventTicketsLocations = ResourceManager.getInstance().getEventTicketBookings();
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

    private void applyFilters() {
        this.filters.clear();

        this.filters.add(new PriceFilterStrategy(
            Double.parseDouble(minPriceField.getText()),
            Double.parseDouble(maxPriceField.getText())));
        this.filters.add(new LocationFilterStrategy(selectedLocations));

        switch(type_lol) {
            case 1:
                this.filters.add(new ApartmentFilterStrategy(minRoomCount, maxRoomCount));
                break;
            case 2:
                this.filters.add(new CarRentalFilterStrategy(selectedCarTypes));
                break;
            case 3:
                this.filters.add(new EventTicketFilterStrategy(selectedEventTypes));
                break;
            default:
                break;
        }
        
        List<Booking> bookings = BookingService.getInstance().filterBookings(this.filters);
        this.listModel.clear();
        for(Booking booking : bookings) {
            this.listModel.addElement(booking);
        }
    }




    private class CarRentButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            listModel.clear();
            List<CarRentalBooking> carRentals = ResourceManager.getInstance().getCarRentalBookings();
            List<CarRentalBooking> carRentals = ResourceManager.getInstance().getCarRentalBookings();
            for (CarRentalBooking carRental : carRentals) {
                BookingService.getInstance().createBooking(carRental);
                listModel.addElement(carRental);
            }

            filtersPanel.removeAll();

            filtersPanel.add(applyFiltersButton);
            filtersPanel.add(Box.createRigidArea(new Dimension(0, 30)));
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
            List<ApartmentBooking> apartmentRentals = ResourceManager.getInstance().getApartmentBookings();
            List<ApartmentBooking> apartmentRentals = ResourceManager.getInstance().getApartmentBookings();
            for (ApartmentBooking booking : apartmentRentals) {
                BookingService.getInstance().createBooking(booking);
                listModel.addElement(booking);
            }
            filtersPanel.removeAll();

            filtersPanel.add(applyFiltersButton);
            filtersPanel.add(Box.createRigidArea(new Dimension(0, 30)));
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

            JLabel ratingLabel = new JLabel("Rating");
            JTextField minRatingField = new JTextField("Min rating");
            filtersPanel.add(roomLabel);
            filtersPanel.add(minRoomCountField);
            filtersPanel.add(Box.createRigidArea(new Dimension(0, 30)));
            filtersPanel.add(ratingLabel);
            filtersPanel.add(minRatingField);
            filtersPanel.revalidate();
            filtersPanel.repaint();
            
            type_lol = 1;
        }
    }

    private class EventTicketsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            listModel.clear();
            List<EventTicketBooking> eventTickets = ResourceManager.getInstance().getEventTicketBookings();
            List<EventTicketBooking> eventTickets = ResourceManager.getInstance().getEventTicketBookings();
            for (EventTicketBooking booking : eventTickets) {
                BookingService.getInstance().createBooking(booking);
                listModel.addElement(booking);
            }

            filtersPanel.removeAll();

            filtersPanel.add(applyFiltersButton);
            filtersPanel.add(Box.createRigidArea(new Dimension(0, 30)));
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
        public void actionPerformed(ActionEvent e) {}


    }

    private class EventFilterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<String> eventTypes = ResourceManager.getInstance().getEventTypes();
            List<String> eventTypes = ResourceManager.getInstance().getEventTypes();
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
                StringBuilder selectedEventsSB = new StringBuilder();
                for (JCheckBox checkBox : checkBoxes) {
                    if (checkBox.isSelected()) {
                        if (selectedEventsSB.length() > 0) {
                            selectedEventsSB.append(", ");
                        }
                        selectedEventsSB.append(checkBox.getText());
                    }
                }
                if (selectedEventsSB.length() == 0) {
                    eventTypeFilterButton.setText("Select Event Types");
                } else {
                    eventTypeFilterButton.setText(selectedEventsSB.toString());
                }
                
                type_lol = 3;
                selectedEventTypes = Arrays.asList(selectedEventsSB.toString().split(","));
            }
        }
    }

    private class CarTypeFilterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<String> carTypes = ResourceManager.getInstance().getCarTypes();
            List<String> carTypes = ResourceManager.getInstance().getCarTypes();
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
                StringBuilder selectedCarTypesSB = new StringBuilder();
                for (JCheckBox checkBox : checkBoxes) {
                    if (checkBox.isSelected()) {
                        if (selectedCarTypesSB.length() > 0) {
                            selectedCarTypesSB.append(", ");
                        }
                        selectedCarTypesSB.append(checkBox.getText());
                    }
                }
                if (selectedCarTypesSB.length() == 0) {
                    carTypeFilterButton.setText("Select Car Types");
                } else {
                    carTypeFilterButton.setText(selectedCarTypesSB.toString());
                }

                type_lol = 2;
                selectedCarTypes = Arrays.asList(selectedCarTypesSB.toString().split(","));
            }
        }
    }
}
