package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMenuPanel extends JPanel {
    private JFrame parentFrame;

    public UserMenuPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        //lewy panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        JButton mainPageButton = new JButton("Main Page");
        mainPageButton.addActionListener(new MainPageButtonListener());
        leftPanel.add(mainPageButton, BorderLayout.NORTH);

        JButton signOutButton = new JButton("Sign Out");
        signOutButton.addActionListener(new SignOutButtonListener());
        leftPanel.add(signOutButton, BorderLayout.SOUTH);

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
            new MainMenuGui();
        }
    }

    private class SignOutButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            parentFrame.dispose();
            new LoginGui();
        }
    }

}
