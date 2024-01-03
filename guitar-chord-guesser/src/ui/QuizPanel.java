package ui;

import core.Fretting;

import javax.swing.*;
import java.awt.*;

public class QuizPanel extends JPanel {
    private FrettingView[] views;
    private int num_of_options;

    public QuizPanel() {
        setLayout(new GridLayout(1, 4));

        num_of_options = 1;

        views = new FrettingView[num_of_options];
        for (int i = 0; i < views.length; i++) {
//            Fretting fretting = new Fretting(-1, 3, 2, 0, 1, 0);
            Fretting fretting = new Fretting(-1, 0, 3, 4, 0, 1, -1, -1, -1);
            views[i] = new FrettingView(fretting);
            add(views[i]);
        }
    }
}
