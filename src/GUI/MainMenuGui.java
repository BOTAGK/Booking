package GUI;

import javax.swing.*;

public class MainMenuGui extends JFrame {
    public MainMenuGui() {
        setTitle("MainMenu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);

        add(new MainMenuPanel(this));

        setVisible(true);

    }
    public static void main(String[] args) {
        new MainMenuGui();
    }

}


