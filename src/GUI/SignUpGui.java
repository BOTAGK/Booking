package GUI;

import javax.swing.*;
import java.awt.*;

public class SignUpGui extends JFrame {
    public SignUpGui() {
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);

        add(new SignUpPanel(this), BorderLayout.NORTH);
        setVisible(true);
    }
}
