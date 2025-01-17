package GUI;

import javax.swing.*;

public class FiltersPanelTest {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        CheckBoxes checkBoxes = new CheckBoxes();
        FiltersDialog filtersDialog = new FiltersDialog(3);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        filtersDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        filtersDialog.setSize(250, 400);
        filtersDialog.setLocationRelativeTo(frame);
        filtersDialog.setVisible(true);
    }
}
