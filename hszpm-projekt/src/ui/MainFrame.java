package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private ConfigPanel configPanel;
//    private EditorPanel editorPanel;

    public MainFrame() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);

        setLayout(new GridBagLayout());

        configPanel = new ConfigPanel();
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        add(configPanel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        add(new JButton(), c);
//        editorPanel

        setVisible(true);
    }
}
