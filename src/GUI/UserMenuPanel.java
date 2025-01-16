package GUI;

import javax.swing.*;

import BookingService.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMenuPanel extends JPanel {
    private JFrame parentFrame;
    private User user;

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

        //panel z informacjami o użytkowniku
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        JLabel labelName = new JLabel("Name");
        JLabel labelLastName = new JLabel("Last Name");
        JLabel labelEmail = new JLabel("Email");
        JButton editButtonInformation = new JButton("Edit information");
        infoPanel.add(labelName);
        infoPanel.add(labelLastName);
        infoPanel.add(labelEmail);
        infoPanel.add(editButtonInformation);

        //panel z listą bookingów
        JPanel bookingsPanel = new JPanel();
        bookingsPanel.setLayout(new BoxLayout(bookingsPanel, BoxLayout.Y_AXIS));
        JLabel labelBookings = new JLabel("Bookings");
        labelBookings.setFont(new Font("Arial", Font.BOLD, 24));
        bookingsPanel.add(labelBookings);

        //panel z zakładakami
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("My bookings", bookingsPanel);
        tabbedPane.addTab("User info", infoPanel);


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

}
