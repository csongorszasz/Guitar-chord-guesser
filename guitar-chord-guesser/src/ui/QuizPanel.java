package ui;

import logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizPanel extends JPanel {
    QuizManager quizManager;

    private FrettingView[] views;
    private MainFrame mainFrame;
    private JButton nextButton;
    private JButton menuButton; /* shows up after completing the quiz */
    private JButton leaveButton; /* is visible during the entirety of the quiz */
    private JButton listenToChordButton;
    private JLabel currentQuestionLabel;

    public QuizPanel(MainFrame mainFrame) {
        setLayout(new GridLayout(1, 4));

        this.mainFrame = mainFrame;

        menuButton = new JButton("Menu");
        menuButton.addActionListener(e -> mainFrame.showLayout(MainFrame.VIEW_MAINMENU));

        leaveButton = new JButton("Leave quiz");
        leaveButton.addActionListener(e -> mainFrame.showLayout(MainFrame.VIEW_MAINMENU));

//        views = new FrettingView[num_of_options];
//        for (int i = 0; i < views.length; i++) {
            /* choose a random chord based on the quiz mode
                * in a question all chords need to have the same type of accidentals.
                  it can't be that one chord is an F# chord and another one is a Bb chord.
                  Either (F# and A#) OR (Gb and Bb). */
            /* check that it's unique compared to the already chosen chords */
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
