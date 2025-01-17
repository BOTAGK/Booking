package GUI;

import javax.swing.*;

import BookingService.User;

public class MainMenuGui extends JFrame {
    public MainMenuGui(User user) {
        setTitle("MainMenu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        add(new MainMenuPanel(this, user));

        setVisible(true);

    }
    public static void main(String[] args) {
        new MainMenuGui(null);
    }

}


