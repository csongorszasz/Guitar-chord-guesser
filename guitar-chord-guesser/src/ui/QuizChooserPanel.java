package ui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class QuizChooserPanel extends JPanel {
    private JLabel infoLabel;

    /* Majors, Minors, Dominant 7th's, Major 7th's, Minor 7th's, Diminished */
    private List<JButton> buttons;

    public QuizChooserPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        int minW = 400;
        int minH = 600;
        setPreferredSize(new Dimension(minW, minH));
        setMinimumSize(new Dimension(minW, minH));
        setMaximumSize(new Dimension(minW, minH));

        infoLabel = new JLabel("Choose a quiz mode");
        infoLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());
        add(Box.createRigidArea(new Dimension(0, 100)));
        add(infoLabel);
        add(Box.createRigidArea(new Dimension(0, 100)));

        JPanel buttonsPanel = new JPanel(new GridLayout(6, 1, 0, 10));
        buttons = new ArrayList<JButton>();
        buttons.add(new JButton("Majors"));
        buttons.add(new JButton("Minors"));
        buttons.add(new JButton("Dominant 7th's"));
        buttons.add(new JButton("Major 7th's"));
        buttons.add(new JButton("Minor 7th's"));
        buttons.add(new JButton("Diminished"));

        for (JButton button : buttons) {
            buttonsPanel.add(button);
        }

        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        buttonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(buttonsPanel);
    }
}
