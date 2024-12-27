package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignUpGui extends JFrame {
    public SignUpGui() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLbl = new JLabel("Booking");
        titleLbl.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLbl, gbc);

        JLabel nameLbl = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(nameLbl, gbc);

        JTextField nameTxt = new JTextField(15);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(nameTxt, gbc);

        JLabel lastNameLbl = new JLabel("Last name:");
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lastNameLbl, gbc);

        JTextField lastNameTxt = new JTextField(15);
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lastNameTxt, gbc);

        JLabel emailLbl = new JLabel("E-mail:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(emailLbl, gbc);

        JTextField emailTxt = new JTextField(15);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(emailTxt, gbc);

        JLabel usernameLbl = new JLabel("Username:");
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(usernameLbl, gbc);

        JTextField usernameTxt = new JTextField(15);
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(usernameTxt, gbc);


        JLabel passwordLbl = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(passwordLbl, gbc);

        JPasswordField txtPassword = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtPassword, gbc);


        JButton signUpButton = new JButton("Sign up");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(signUpButton, gbc);
        add(panel, BorderLayout.NORTH);
        setVisible(true);
    }
}
