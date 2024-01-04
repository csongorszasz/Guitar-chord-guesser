package ui;

import core.Fretting;
import core.SoundPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizPanel extends JPanel {
    private FrettingView[] views;
    private int num_of_options;
    private MainFrame mainFrame;
    private JButton nextButton;
    private JButton menuButton;
    private JButton listenToChordButton;

    public QuizPanel(MainFrame mainFrame) {
        setLayout(new GridLayout(1, 4));

        this.mainFrame = mainFrame;

        num_of_options = 4;

        views = new FrettingView[num_of_options];
        for (int i = 0; i < views.length; i++) {
//            Fretting fretting = new Fretting(-1, 3, 2, 0, 1, 0);
//            Fretting fretting = new Fretting(-1, 0, 3, 4, 0, 1, 0, 5, 1);
//            Fretting fretting = new Fretting(3, 2, 0, 0, 3, 3);
//            Fretting fretting = new Fretting(3, 2, 0, 0, 3, 3);
            Fretting fretting = new Fretting(6, 8, 6, 6, 6, -1, 0, 5, 6);
            views[i] = new FrettingView(fretting);
            add(views[i]);
        }

        listenToChordButton = new JButton("Listen to chord");
        add(listenToChordButton);
        listenToChordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundPlayer.getInstance().playChord(/* chord.getFretting */);
            }
        });
    }
}
