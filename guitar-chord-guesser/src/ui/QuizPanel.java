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
    private JLabel currentQuestionLabel;

    public QuizPanel(MainFrame mainFrame, QuizManager quizManager) {
        setLayout(new GridLayout(1, 4));

        this.mainFrame = mainFrame;
        this.quizManager = quizManager;

        quizOptionsGridPanel = new QuizOptionsGridPanel(quizManager);
        add(quizOptionsGridPanel);

        menuButton = new JButton("Menu");
        menuButton.addActionListener(e -> mainFrame.showLayout(MainFrame.VIEW_MAINMENU));

        leaveButton = new JButton("Leave quiz");
        leaveButton.addActionListener(e -> mainFrame.showLayout(MainFrame.VIEW_MAINMENU));

//        views = new FrettingView[num_of_options];
//        for (int i = 0; i < views.length; i++) {
            /* if it's unique, add it to the view, else choose again */
//        }

        listenToChordButton = new JButton("Listen to chord");
        add(listenToChordButton);
        listenToChordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* play the chord in question */
//                SoundPlayer.getInstance().playChord(/* chord.getFretting */);
            }
        });
    }
}
