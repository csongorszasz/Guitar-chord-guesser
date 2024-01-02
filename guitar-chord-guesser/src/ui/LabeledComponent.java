package ui;

import javax.swing.*;
import java.awt.*;

public class LabeledComponent extends JPanel {
    private JLabel label;
    private JComponent component;

    public LabeledComponent(String text, JComponent component) {
        setLayout(new GridLayout());
        setPreferredSize(new Dimension(-1, 40));

        label = new JLabel(text);
        this.component = component;

        add(label);
        add(this.component);
    }

    public JLabel getLabel() {
        return label;
    }

    public JComponent getComponent() {
        return component;
    }
}

