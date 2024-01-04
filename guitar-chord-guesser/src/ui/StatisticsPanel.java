package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StatisticsPanel extends JPanel {
    private MainFrame mainFrame;
    private JButton menuButton;

    private JLabel titleLabel;
    private List<JLabel> highScoreLabels;

    public StatisticsPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        menuButton = new JButton("Menu");
        menuButton.addActionListener(e -> mainFrame.showLayout(MainFrame.VIEW_MAINMENU));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.mainFrame = mainFrame;

        titleLabel = new JLabel("Statistics");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0, 100)));
        add(titleLabel);
        add(Box.createVerticalGlue());

        JPanel highScoresPanel = new JPanel();
        highScoresPanel.setLayout(new BoxLayout(highScoresPanel, BoxLayout.Y_AXIS));

        JLabel highScoreslabel = new JLabel("High scores");
        highScoreslabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        highScoreslabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(highScoreslabel);
        add(Box.createRigidArea(new Dimension(0, 10)));

        highScoreLabels = new ArrayList<JLabel>();
        highScoreLabels.add(new JLabel("Majors: " + "/10"));
        highScoreLabels.add(new JLabel("Minors: " + "/10"));
        highScoreLabels.add(new JLabel("Dominant 7th's: " + "/10"));
        highScoreLabels.add(new JLabel("Major 7th's: " + "/10"));
        highScoreLabels.add(new JLabel("Minor 7th's: " + "/10"));
        highScoreLabels.add(new JLabel("Diminished: " + "/10"));

        for (int i = 0; i < highScoreLabels.size(); i++) {
            highScoreLabels.get(i).setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
            highScoresPanel.add(highScoreLabels.get(i));
        }

        highScoresPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(highScoresPanel);
        add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel averageScoreLabel = new JLabel("Average score: " + "/10");
        averageScoreLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        averageScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(averageScoreLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(averageScoreLabel);

        add(Box.createVerticalGlue());

        menuButton = new JButton("Menu");
        menuButton.addActionListener(e -> mainFrame.showLayout(MainFrame.VIEW_MAINMENU));
        menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(menuButton);
        add(Box.createRigidArea(new Dimension(0, 100)));
    }
}
