package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainMenuPanel extends JPanel {
    private JButton statisticsButton;
    private MainFrame mainFrame;

    private JLabel titleLabel;

    /* Majors, Minors, Dominant 7th's, Major 7th's, Minor 7th's, Diminished */
    private List<JButton> modeButtons;

    public MainMenuPanel(MainFrame mainFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.mainFrame = mainFrame;

        titleLabel = new JLabel("Choose a quiz mode");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0, 100)));
        add(titleLabel);
        add(Box.createVerticalGlue());

        JPanel buttonsPanel = new JPanel(new GridLayout(6, 1));
        modeButtons = new ArrayList<JButton>();
        modeButtons.add(new JButton("Majors"));
        modeButtons.add(new JButton("Minors"));
        modeButtons.add(new JButton("Dominant 7th's"));
        modeButtons.add(new JButton("Major 7th's"));
        modeButtons.add(new JButton("Minor 7th's"));
        modeButtons.add(new JButton("Diminished"));

        for (int i = 0; i < modeButtons.size(); i++) {
            buttonsPanel.add(modeButtons.get(i));
        }

        add(buttonsPanel);
        add(Box.createVerticalGlue());

        statisticsButton = new JButton("Statistics");
        statisticsButton.addActionListener(e -> mainFrame.showLayout(MainFrame.VIEW_STATISTICS));
        statisticsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(statisticsButton);
        add(Box.createRigidArea(new Dimension(0, 100)));
    }
}
