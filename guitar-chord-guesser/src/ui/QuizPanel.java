package ui;

import logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizPanel extends JPanel {
    private QuizManager quizManager;
    private QuizOptionsGridPanel quizOptionsGridPanel;
    private MainFrame mainFrame;
    private JButton nextButton;
    private JButton menuButton; /* shows up after completing the quiz */
    private JButton leaveButton; /* is visible during the entirety of the quiz */
    private JButton listenToChordButton;
    private JLabel questionNumberLabel;
    private JLabel currentQuestionLabel;

    public QuizPanel(MainFrame mainFrame, QuizManager quizManager) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.mainFrame = mainFrame;
        this.quizManager = quizManager;
        this.quizManager.setQuizPanel(this);

        add(Box.createRigidArea(new Dimension(-1, 50)));
        questionNumberLabel = new JLabel();
        questionNumberLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30));
        questionNumberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(questionNumberLabel);

        add(Box.createRigidArea(new Dimension(-1, 150)));
        quizOptionsGridPanel = new QuizOptionsGridPanel(quizManager);
        quizOptionsGridPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(quizOptionsGridPanel);
        add(Box.createRigidArea(new Dimension(-1, 100)));

        currentQuestionLabel = new JLabel();
        currentQuestionLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 70));
        currentQuestionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(currentQuestionLabel);
        add(Box.createRigidArea(new Dimension(-1, 100)));

        menuButton = new JButton("Back to menu");
        menuButton.addActionListener(e -> {
            quizManager.saveScore(); /* TODO */
            mainFrame.showLayout(MainFrame.VIEW_MAINMENU);
        });
        menuButton.setVisible(false);

        leaveButton = new JButton("Leave quiz");
        leaveButton.addActionListener(e -> mainFrame.showLayout(MainFrame.VIEW_MAINMENU));

        listenToChordButton = new JButton("Listen to chord");
        listenToChordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundPlayer.getInstance().playChord(quizManager.getChosenChords()[quizManager.getCorrectAnswer()].getFretting());
            }
        });

        JPanel bottomButtonsPanel = new JPanel();
        bottomButtonsPanel.setLayout(new BoxLayout(bottomButtonsPanel, BoxLayout.X_AXIS));
        bottomButtonsPanel.add(Box.createRigidArea(new Dimension(50, -1)));
        bottomButtonsPanel.add(leaveButton);
        bottomButtonsPanel.add(Box.createHorizontalGlue());
        bottomButtonsPanel.add(menuButton);
        bottomButtonsPanel.add(Box.createHorizontalGlue());
        bottomButtonsPanel.add(listenToChordButton);
        bottomButtonsPanel.add(Box.createRigidArea(new Dimension(50, -1)));
        add(bottomButtonsPanel);

        add(Box.createRigidArea(new Dimension(-1, 50)));
    }

    public void update() {
        questionNumberLabel.setText("Question " + quizManager.getQuestionNumber() + "/10");
        currentQuestionLabel.setText(quizManager.getChosenChords()[quizManager.getCorrectAnswer()].toString());
    }
}
