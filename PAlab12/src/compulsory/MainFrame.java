package compulsory;

import javax.swing.*;
import java.awt.*;

/**
 * The main and only frame of the application.
 * Consists of two panels: the Control Panel and the Design Panel
 */
public class MainFrame extends JFrame {


    public MainFrame() throws HeadlessException {
        this.setSize(800, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Designer");
        this.setVisible(true);

        DesignPanel designPanel = new DesignPanel();
        this.add(designPanel, BorderLayout.CENTER);

        ControlPanel controlPanel = new ControlPanel(designPanel);
        this.add(controlPanel, BorderLayout.NORTH);

        pack();
    }
}
