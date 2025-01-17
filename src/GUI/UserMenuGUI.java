package GUI;

import javax.swing.*;

import BookingService.User;

public class UserMenuGUI extends JFrame {
    public UserMenuGUI(User user){
        setTitle("UserMenu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        add(new UserMenuPanel(this, user));

        setVisible(true);

    }

    public static void main(String[] args) {
        new UserMenuGUI(null);
    }
}
