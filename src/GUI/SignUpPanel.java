package GUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.List;

public class SignUpPanel extends JPanel {


    private JTextField nameTxt, lastNameTxt, emailTxt, usernameTxt;
    private JPasswordField txtPassword;

    public SignUpPanel(JFrame parentFrame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLbl = new JLabel("Booking");
        titleLbl.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLbl, gbc);

        JLabel nameLbl = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(nameLbl, gbc);

        // Inicjalizacja pól formularza
        nameTxt = new JTextField(15);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(nameTxt, gbc);

        JLabel lastNameLbl = new JLabel("Last name:");
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(lastNameLbl, gbc);

        lastNameTxt = new JTextField(15);
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.WEST;
        add(lastNameTxt, gbc);

        JLabel emailLbl = new JLabel("E-mail:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(emailLbl, gbc);

        emailTxt = new JTextField(15);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(emailTxt, gbc);

        JLabel usernameLbl = new JLabel("Username:");
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(usernameLbl, gbc);

        usernameTxt = new JTextField(15);
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.WEST;
        add(usernameTxt, gbc);

        JLabel passwordLbl = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(passwordLbl, gbc);

        txtPassword = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtPassword, gbc);

        JButton signUpButton = new JButton("Sign up");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        add(signUpButton, gbc);

        JLabel statusLbl = new JLabel(" ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        add(statusLbl, gbc);


        List<String> takenUsernames = List.of("admin", "user1", "guest");

        signUpButton.addActionListener(e -> {

            if (validateFields(takenUsernames)) {
                JOptionPane.showMessageDialog(this, "You have successfully signed up!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }


    private boolean validateFields(List<String> takenUsernames) {

        String name = nameTxt.getText().trim();
        String lastName = lastNameTxt.getText().trim();
        String email = emailTxt.getText().trim();
        String username = usernameTxt.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (takenUsernames.contains(username)) {
            JOptionPane.showMessageDialog(this, "Username is already taken!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(this, "Invalid email address!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}
