package GUI;

import javax.swing.*;

public class MainMenuGui extends JFrame {
    public MainMenuGui() {
        setTitle("MainMenu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        add(new MainMenuPanel(this));

        setVisible(true);

    }
    public static void main(String[] args) {
        new MainMenuGui();
    }

}


