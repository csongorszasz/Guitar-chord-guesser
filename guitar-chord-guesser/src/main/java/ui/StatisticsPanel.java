package main.java.ui;

import main.java.logic.QuizManager;
import main.java.logic.QuizRound;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StatisticsPanel extends JPanel {
    private MainFrame mainFrame;
    private JButton menuButton;
    private QuizManager quizManager;

    private JLabel titleLabel;
    private List<JLabel> highScoreLabels;
    private JLabel averageScoreLabel;

    public StatisticsPanel(MainFrame mainFrame, QuizManager quizManager) {
        this.mainFrame = mainFrame;
        this.quizManager = quizManager;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        menuButton = new JButton("Menu");
        menuButton.addActionListener(e -> mainFrame.showLayout(MainFrame.VIEW_MAINMENU));

        this.mainFrame = mainFrame;

        titleLabel = new JLabel("Statistics");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel highScoresPanel = new JPanel();
        highScoresPanel.setLayout(new BoxLayout(highScoresPanel, BoxLayout.Y_AXIS));
        highScoresPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel highScoreslabel = new JLabel("High scores");
        highScoreslabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        highScoreslabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        highScoreLabels = new ArrayList<JLabel>();
        for (int i = 0; i < quizManager.getQuizModes().size(); i++) {
            highScoreLabels.add(new JLabel());
            highScoreLabels.get(i).setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
            highScoreLabels.get(i).setAlignmentX(Component.RIGHT_ALIGNMENT);
            highScoresPanel.add(highScoreLabels.get(i));
        }

        averageScoreLabel = new JLabel();
        averageScoreLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        averageScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        menuButton = new JButton("Menu");
        menuButton.addActionListener(e -> mainFrame.showLayout(MainFrame.VIEW_MAINMENU));
        menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0, 100)));
        add(titleLabel);
        add(Box.createVerticalGlue());
        add(highScoreslabel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(highScoresPanel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(averageScoreLabel);
        add(Box.createVerticalGlue());
        add(menuButton);
        add(Box.createRigidArea(new Dimension(0, 100)));
    }

    public void update() {
        for (int i = 0; i < quizManager.getQuizModes().size(); i++) {
            final int quizMode = i;
            int highScore = quizManager.getScores().stream()
                    .filter(s -> s.getQuizMode() == quizMode)
                    .max(Comparator.comparing(QuizRound::getCorrectAnswers))
                        .orElse(new QuizRound(quizMode,-1))
                    .getCorrectAnswers();
            if (highScore == -1) {
                highScoreLabels.get(i).setText(quizManager.getQuizModes().get(i) + ": " + " " + "/10");
            } else {
                highScoreLabels.get(i).setText(quizManager.getQuizModes().get(i) + ": " + highScore + "/10");
            }
        }

        double averageScore = quizManager.getScores().stream()
                .mapToDouble(QuizRound::getCorrectAnswers)
                .average().orElse(0.0);
        averageScoreLabel.setText("Overall average score: " + String.format("%.2f", averageScore) + "/10");
    }
}
