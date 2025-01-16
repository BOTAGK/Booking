package GUI;

import javax.swing.*;

import BookingService.User;
import BookingService.UserList;
import Exceptions.InvalidLoginException;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class LoginPanel extends JPanel {
    public LoginPanel(JFrame parentFrame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLbl = new JLabel("Booking");
            titleLbl.setFont(new Font("Arial", Font.BOLD, 24));
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            add(titleLbl, gbc);

        JLabel usernameLbl = new JLabel("Username:");
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.EAST;
            add(usernameLbl, gbc);

        JTextField usernameTxt = new JTextField(15);
            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            add(usernameTxt, gbc);

        JLabel passwordLbl = new JLabel("Password:");
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.EAST;
            add(passwordLbl, gbc);

        JPasswordField txtPassword = new JPasswordField(15);
            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            add(txtPassword, gbc);

        JLabel signUplbl = new JLabel("<html> <a href = '#'>Sign Up</a></html>");
            signUplbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.CENTER;
            add(signUplbl, gbc);

            signUplbl.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    parentFrame.dispose();
                    new SignUpGui();
                }
            });

        JButton loginButton = new JButton("Login");
            gbc.gridx = 1;
            gbc.gridy = 5;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            add(loginButton, gbc);

        JLabel errorLbl = new JLabel(" ");
            errorLbl.setVisible(false);
            errorLbl.setForeground(Color.RED);
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 4;
            gbc.anchor = GridBagConstraints.CENTER;
            add(errorLbl, gbc);

            loginButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {         
                    User user = null;
                    
                    try {
                        user = UserList.getInstance().loginAttempt(
                            usernameTxt.getText(), 
                            String.valueOf(txtPassword.getPassword()));
                    } catch(InvalidLoginException exception) {
                        errorLbl.setText(exception.getMessage());
                        errorLbl.setVisible(true);
                        return;
                    }

                    // Debugging
                    Objects.requireNonNull(user);

                    parentFrame.dispose();
                    new MainMenuGui(user);
                }
            });
    }
}
