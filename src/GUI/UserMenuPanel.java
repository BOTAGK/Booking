package GUI;

import javax.swing.*;

import Booking.Booking;
import BookingService.*;
import Exceptions.BlankFieldException;
import Exceptions.CredentialsTakenException;
import Exceptions.InputValidationFailureException;
import Exceptions.InvalidSyntaxException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class UserMenuPanel extends JPanel {
    private JFrame parentFrame;
    private User user;
    private JLabel labelName;
    private JLabel labelLastName;
    private JLabel labelEmail;
    private JLabel labelUsername;

    public UserMenuPanel(JFrame parentFrame, User user) {
        this.parentFrame = parentFrame;
        this.user = user;
        //lewy panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        JButton mainPageButton = new JButton("Main Page");
        mainPageButton.addActionListener(new MainPageButtonListener());
        leftPanel.add(mainPageButton, BorderLayout.NORTH);

        JButton logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(new LogOutButtonListener());
        leftPanel.add(logOutButton, BorderLayout.SOUTH);

        //panel z informacjami o użytkowniku i opcją edycji
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        Font labelFont =new Font("Arial MS", Font.BOLD, 18);
        labelName = new JLabel("Name: "+ user.getName());
        labelLastName = new JLabel("Last Name: "+ user.getLastname());
        labelEmail = new JLabel("Email: "+ user.getEmail());
        labelUsername = new JLabel("Username: "+ user.getUsername());
        labelUsername.setFont(labelFont);
        labelName.setFont(labelFont);
        labelLastName.setFont(labelFont);
        labelEmail.setFont(labelFont);
        infoPanel.add(labelName);
        infoPanel.add(labelLastName);
        infoPanel.add(labelUsername);
        infoPanel.add(labelEmail);

        JPanel editButtonPanel = new JPanel();
        editButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton editInformationButton = new JButton("Edit information");
        editInformationButton.addActionListener(new EditInformationButtonListener());
        editButtonPanel.add(editInformationButton);

        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(editButtonPanel, BorderLayout.SOUTH);

        //panel z listą bookingów
        JPanel bookingsPanel = new JPanel();
        bookingsPanel.setLayout(new BoxLayout(bookingsPanel, BoxLayout.Y_AXIS));
        DefaultListModel listModel = new DefaultListModel<>();
        for(BookingId myBooking : user.getBookingIds()){
            listModel.addElement(BookingService.getInstance().getBooking(myBooking));
        }

        JList<Booking> bookingsInUserMenu = new JList<>(listModel);
        Font font =new Font("Arial MS", Font.PLAIN, 24);
        bookingsInUserMenu.setFont(font);
        bookingsInUserMenu.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Booking){
                    setText(value.toString());
                    Image image=((Booking) value).getIcon().getImage();
                    image=image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    ImageIcon icon=new ImageIcon(image);
                    setIcon(icon);
                }

                return this;
            }
        });
        bookingsInUserMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = bookingsInUserMenu.locationToIndex(e.getPoint());
                    Booking selectedBooking = bookingsInUserMenu.getModel().getElementAt(index);
                    int result = JOptionPane.showConfirmDialog(null, "Do you want to cancel this booking?\n" + selectedBooking, "Confirm your cancelation", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        // Dodaj logikę rezerwacji
                        BookingId bookingId = BookingService.getInstance().getBookingId(selectedBooking);
                        if (bookingId != null) {
                            BookingService.getInstance().unbookBooking(user, bookingId);
                            System.out.println("Booking canceled: " + selectedBooking);
                            parentFrame.dispose();
                            new UserMenuGUI(user);
                        } else {
                            System.out.println("Booking ID is null for selected booking: " + selectedBooking);
                        }
                    }
                }
            }
        });

        DefaultListModel observedModel = new DefaultListModel<>();
        for(BookingId myBooking : BookingService.getInstance().getUsersObservables(user)){
            observedModel.addElement(BookingService.getInstance().getBooking(myBooking));
        }
        JList<Booking> observedList = new JList<>(observedModel);
        observedList.setFont(font);
        observedList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Booking){
                    setText(value.toString());
                    Image image=((Booking) value).getIcon().getImage();
                    image=image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    ImageIcon icon=new ImageIcon(image);
                    setIcon(icon);
                }

                return this;
            }
        });

        observedList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = observedList.locationToIndex(e.getPoint());
                    Booking selectedBooking = observedList.getModel().getElementAt(index);
                    int result = JOptionPane.showConfirmDialog(null, "Do you want to quit observing this booking?\n" + selectedBooking, "Confirm your cancelation", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        // Dodaj logikę rezerwacji
                        BookingId bookingId = BookingService.getInstance().getBookingId(selectedBooking);
                        if (bookingId != null) {
                            BookingService.getInstance().removeObserver(user, bookingId);
                            System.out.println("Observing canceled: " + selectedBooking);
                            parentFrame.dispose();
                            new UserMenuGUI(user);
                        } else {
                            System.out.println("Booking ID is null for selected booking: " + selectedBooking);
                        }
                    }
                }
            }
        });
        JPanel observedPanel = new JPanel();
        observedList.setLayout(new BoxLayout(observedList, BoxLayout.Y_AXIS));
        JScrollPane scrollPane1 = new JScrollPane(observedList);
        bookingsInUserMenu.setLayout(new BoxLayout(bookingsInUserMenu, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(bookingsInUserMenu);
        bookingsPanel.setLayout(new BoxLayout(bookingsPanel, BoxLayout.Y_AXIS));
        observedPanel.setLayout(new BoxLayout(observedPanel, BoxLayout.Y_AXIS));
        bookingsPanel.add(scrollPane, BorderLayout.EAST);
        observedPanel.add(scrollPane1, BorderLayout.EAST);
        setVisible(true);
        //panel z zakładakami
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("My bookings", bookingsPanel);
        tabbedPane.addTab("Observed bookings", observedPanel);
        tabbedPane.addTab("User info", panel);

        //dodanie do panelu główneg
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);
    }

    private class MainPageButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            parentFrame.dispose();
            new MainMenuGui(user);
        }
    }

    private class LogOutButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            parentFrame.dispose();
            new LoginGui();
        }
    }

    private class EditInformationButtonListener implements ActionListener {
        JDialog dialogEditInformation;
        JTextField textFieldName;
        JTextField textFieldLastName;
        JTextField textFieldEmail;
        JTextField textFieldUsername;
        JLabel statusLabel;
        @Override
        public void actionPerformed(ActionEvent e) {
            //tworzenie dialogu
            dialogEditInformation = new JDialog();
            dialogEditInformation.setTitle("Edit information");
            dialogEditInformation.setSize(300,200);

            //tworzenie panelu edycji
            JPanel dialogPanel = new JPanel();
            dialogPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

            JPanel editPanel = new JPanel();
            editPanel.setLayout(new GridLayout(4,2));

            JLabel dialogLabelName = new JLabel("Name: ");
            JLabel dialogLabelLastName = new JLabel("Lastname: ");
            JLabel dialogLabelEmail = new JLabel("Email: ");
            JLabel dialogLabelUsername = new JLabel("Username: ");

            textFieldName = new JTextField();
            textFieldName.setText(user.getName());
            textFieldName.setSize(10,5);
            textFieldLastName = new JTextField();
            textFieldLastName.setText(user.getLastname());
            textFieldLastName.setSize(10,5);
            textFieldEmail = new JTextField();
            textFieldEmail.setText(user.getEmail());
            textFieldEmail.setSize(10, 5);
            textFieldUsername = new JTextField();
            textFieldUsername.setText(user.getUsername());
            textFieldName.setSize(10,5);


            editPanel.add(dialogLabelName);
            editPanel.add(textFieldName);
            editPanel.add(dialogLabelLastName);
            editPanel.add(textFieldLastName);
            editPanel.add(dialogLabelUsername);
            editPanel.add(textFieldUsername);
            editPanel.add(dialogLabelEmail);
            editPanel.add(textFieldEmail);

            JPanel messagePanel = new JPanel();
            messagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            statusLabel = new JLabel(" ");
            statusLabel.setForeground(Color.RED);
            messagePanel.add(statusLabel);

            dialogPanel.add(editPanel);
            dialogPanel.add(messagePanel);

            //tworzenie panelu przycisków
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            JButton saveButton = new JButton("Save");
            JButton cancelButton = new JButton("Cancel");
            saveButton.addActionListener(new SaveButtonListener());
            cancelButton.addActionListener(new CancelButtonListener());
            buttonPanel.add(saveButton);
            buttonPanel.add(cancelButton);

            //dodanie do dialogu
            dialogEditInformation.add(dialogPanel,BorderLayout.CENTER);
            dialogEditInformation.add(buttonPanel, BorderLayout.SOUTH);
            dialogEditInformation.setVisible(true);
        }

        private class SaveButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validateFields(UserList.getInstance().getTakenUsernames(), UserList.getInstance().getTakenEmails())){
                    user.setName(textFieldName.getText());
                    System.out.println(textFieldName.getText());
                    labelName.setText("Name: " + user.getName());
                    user.setLastname(textFieldLastName.getText());
                    labelLastName.setText("Last name: " + user.getLastname());
                    user.setUsername(textFieldUsername.getText());
                    labelUsername.setText("Username: " + user.getUsername());
                    user.setEmail(textFieldEmail.getText());
                    labelEmail.setText("Email: " + user.getEmail());
                    System.out.println("zedytowane");
                    dialogEditInformation.dispose();
                }
                else{
                    System.out.println("porażka");
                }
            }
            private boolean validateFields(java.util.List<String> takenUsernames, List<String> takenEmails) {
                takenUsernames.remove(user.getUsername());
                takenEmails.remove(user.getEmail());
                String username = textFieldUsername.getText();
                String name = textFieldName.getText().trim();
                String lastName = textFieldLastName.getText().trim();
                String email = textFieldEmail.getText().trim();

                try {
                    if (name.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
                        throw new BlankFieldException("Fields cannot be blank");
                    }

                    if (takenEmails.contains(email)) {
                        throw new CredentialsTakenException("Email is already taken");
                    }

                    if (takenUsernames.contains(username)) {
                        throw new CredentialsTakenException("Username is already taken");
                    }

                    if (!email.contains("@") || !email.contains(".")) {
                        throw new InvalidSyntaxException("Email is invalid");
                    }
                } catch (InputValidationFailureException exception) {
                    statusLabel.setText(exception.getMessage());
                    return false;
                }

                return true;
            }
        }

        private class CancelButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogEditInformation.dispose();
            }
        }

    }

}
