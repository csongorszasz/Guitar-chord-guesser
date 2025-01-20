package main.java.ui;

import main.java.logic.*;

import javax.swing.*;
import java.awt.*;

public class QuizPanel extends JPanel {
    private QuizManager quizManager;
    private QuizOptionsGridPanel quizOptionsGridPanel;
    private MainFrame mainFrame;
    private JPanel buttonsPanel;
    private JPanel topLabelsPanel;
    private JButton menuButton;
    private JButton leaveButton;
    private JButton nextButton;
    private JButton listenToChordButton;
    private JLabel questionNumberLabel;
    private JLabel currentQuestionLabel;
    private JLabel feedbackLabel;
    private JLabel summaryLabel;

    public QuizPanel(MainFrame mainFrame, QuizManager quizManager) {
        this.mainFrame = mainFrame;
        this.quizManager = quizManager;
        this.quizManager.setQuizPanel(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        questionNumberLabel = new JLabel();
        questionNumberLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30));
        questionNumberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        quizOptionsGridPanel = new QuizOptionsGridPanel(quizManager);
        quizOptionsGridPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        currentQuestionLabel = new JLabel();
        currentQuestionLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 70));
        currentQuestionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        feedbackLabel = new JLabel();
        feedbackLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        summaryLabel = new JLabel();
        summaryLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 40));
        summaryLabel.setForeground(new Color(77, 77, 255, 255));
        summaryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        menuButton = new JButton("Back to menu");
        menuButton.addActionListener(e -> {
            mainFrame.showLayout(MainFrame.VIEW_MAINMENU);
            SoundPlayer.getInstance().playRandomSong();
        });

        leaveButton = new JButton("Leave quiz");
        leaveButton.addActionListener(e -> {
            mainFrame.showLayout(MainFrame.VIEW_MAINMENU);
            SoundPlayer.getInstance().playRandomSong();
        });

        nextButton = new JButton("Next question");
        nextButton.addActionListener(e -> quizManager.nextQuestion());

        listenToChordButton = new JButton("Listen to chord");
        listenToChordButton.addActionListener(e -> SoundPlayer.getInstance().playChord(quizManager.getChosenChords()[quizManager.getCorrectAnswer()].getFretting()));

        initView();
    }

    private void initView() {
        topLabelsPanel = new JPanel();
        topLabelsPanel.setLayout(new BoxLayout(topLabelsPanel, BoxLayout.Y_AXIS));
        topLabelsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topLabelsPanel.add(questionNumberLabel);
        topLabelsPanel.add(summaryLabel);
        summaryLabel.setVisible(false);

        add(Box.createVerticalStrut(100));
        add(topLabelsPanel);
        add(Box.createVerticalStrut(100));

        add(quizOptionsGridPanel);
        add(currentQuestionLabel);
        add(feedbackLabel);
        feedbackLabel.setVisible(true);
        add(Box.createVerticalGlue());

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(leaveButton);
        buttonsPanel.add(listenToChordButton);
        buttonsPanel.add(nextButton);
        buttonsPanel.add(Box.createHorizontalGlue());

        add(buttonsPanel);
        add(Box.createRigidArea(new Dimension(0, 50)));

    }

    public void showQuestionView() {
        feedbackLabel.setVisible(false);
        summaryLabel.setVisible(false);
        nextButton.setEnabled(false);
    }

    public void showAnsweredView() {
        feedbackLabel.setVisible(true);
        nextButton.setEnabled(true);
    }

    public void showEndedView() {
        nextButton.setEnabled(false);
        feedbackLabel.setVisible(true);
        questionNumberLabel.setText("Quiz ended");

        summaryLabel.setText("Correct answers: " + quizManager.getCntCorreclyAnswered() + "/" + quizManager.getNumOfQuestions());
        summaryLabel.setVisible(true);
    }

    public void updateInfoLabels() {
        questionNumberLabel.setText("Question " + quizManager.getQuestionNumber() + "/" + quizManager.getNumOfQuestions());
        currentQuestionLabel.setText(quizManager.getChosenChords()[quizManager.getCorrectAnswer()].toString());
    }

    public void updateFeedbackLabelCorrect() {
        feedbackLabel.setText("Correct");
        feedbackLabel.setForeground(new Color(50, 255, 65, 255));
    }

    public void updateFeedbackLabelWrong() {
        feedbackLabel.setText("Wrong answer");
        feedbackLabel.setForeground(new Color(255, 43, 43, 255));
    }
}
