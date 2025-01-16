package GUI;

import javax.swing.*;

import BookingService.User;
import Booking.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMenuPanel extends JPanel {
    private JFrame parentFrame;
    private User user;
    private JLabel labelName;
    private JLabel labelLastName;
    private JLabel labelEmail;

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
        labelName = new JLabel("Name: "+ user.getName());
        labelLastName = new JLabel("Last Name: "+ user.getLastname());
        labelEmail = new JLabel("Email: "+ user.getEmail());
        infoPanel.add(labelName);
        infoPanel.add(labelLastName);
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

        //panel z zakładakami
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("My bookings", bookingsPanel);
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
        @Override
        public void actionPerformed(ActionEvent e) {
            //tworzenie dialogu
            dialogEditInformation = new JDialog();
            dialogEditInformation.setTitle("Edit information");
            dialogEditInformation.setSize(300,200);

            //tworzenie panelu edycji
            JPanel editPanel = new JPanel();
            editPanel.setLayout(new GridLayout(4,2));

            JLabel dialogLabelName = new JLabel("Name: ");
            JLabel dialogLabelLastName = new JLabel("Lastname: ");
            JLabel dialogLabelEmail = new JLabel("Emial: ");

            textFieldName = new JTextField();
            textFieldName.setText(user.getName());
            textFieldName.setSize(10,5);
            textFieldLastName = new JTextField();
            textFieldLastName.setText(user.getLastname());
            textFieldLastName.setSize(10,5);
            textFieldEmail = new JTextField();
            textFieldEmail.setText(user.getEmail());
            textFieldEmail.setSize(10, 5);

            editPanel.add(dialogLabelName);
            editPanel.add(textFieldName);
            editPanel.add(dialogLabelLastName);
            editPanel.add(textFieldLastName);
            editPanel.add(dialogLabelEmail);
            editPanel.add(textFieldEmail);

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
            dialogEditInformation.add(editPanel,BorderLayout.CENTER);
            dialogEditInformation.add(buttonPanel, BorderLayout.SOUTH);
            dialogEditInformation.setVisible(true);
        }

        private class SaveButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setName(textFieldName.getText());
                System.out.println(textFieldName.getText());
                labelName.setText("Name: " + user.getName());
                user.setLastname(textFieldLastName.getText());
                labelLastName.setText("Last name: " + user.getLastname());
                user.setEmail(textFieldEmail.getText());
                labelEmail.setText("Emial: " + user.getEmail());
                dialogEditInformation.dispose();
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
