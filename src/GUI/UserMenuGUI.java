package GUI;

import javax.swing.*;

public class UserMenuGUI extends JFrame {
    public UserMenuGUI(){
        setTitle("UserMenu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        add(new UserMenuPanel(this));
        setVisible(true);
    }

    public static void main(String[] args) {
        new UserMenuGUI();
    }
}
