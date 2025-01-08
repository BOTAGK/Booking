package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginGui extends JFrame {//todo dialog
    public LoginGui() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        add(new LoginPanel(this));

        setVisible(true);

    }
    public static void main(String[] args) {
        new LoginGui();
    }

}