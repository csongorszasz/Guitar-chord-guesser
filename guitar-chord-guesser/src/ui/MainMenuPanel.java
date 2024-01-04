package ui;

import logic.QuizManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainMenuPanel extends JPanel {
    private MainFrame mainFrame;
    private QuizManager quizManager;

    private JLabel titleLabel;
    private JPanel buttonsPanel;
    private List<JButton> modeButtons;
    private JButton statisticsButton;

    public MainMenuPanel(MainFrame mainFrame, QuizManager quizManager) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.mainFrame = mainFrame;

        this.quizManager = quizManager;

        titleLabel = new JLabel("Choose a quiz mode");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0, 100)));
        add(titleLabel);
        add(Box.createVerticalGlue());

        buttonsPanel = new JPanel(new GridLayout(0, 1));
        modeButtons = new ArrayList<JButton>();
        addQuizModeButtons();
        add(Box.createVerticalGlue());

        statisticsButton = new JButton("Statistics");
        statisticsButton.addActionListener(e -> mainFrame.showLayout(MainFrame.VIEW_STATISTICS));
        statisticsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(statisticsButton);
        add(Box.createRigidArea(new Dimension(0, 100)));
    }

    private void addQuizModeButtons() {
        addQuizModeButton("Majors");
        addQuizModeButton("Minors");
        addQuizModeButton("Dominant 7th's");
        addQuizModeButton("Major 7th's");
        addQuizModeButton("Minor 7th's");
        addQuizModeButton("Diminished");
        addQuizModeButton("ALL");
        add(buttonsPanel);
    }

    private void addQuizModeButton(String name) {
        quizManager.addQuizMode(name);
        JButton button = new JButton(name);
        button.addActionListener(e -> {
            quizManager.setQuizMode(modeButtons.size());
            quizManager.startQuiz();
            mainFrame.showLayout(MainFrame.VIEW_QUIZ);
        });
        modeButtons.add(button);
        buttonsPanel.add(button);
    }
}
