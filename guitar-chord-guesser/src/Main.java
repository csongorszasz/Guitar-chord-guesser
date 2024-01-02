import core.Fretting;
import core.SoundPlayer;
import ui.FrettingView;
import ui.FrettingViewGrid;
import ui.QuizPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    public Main() {
//        setLayout(null);
//        JButton BbButton = new JButton("Bb");
//        add(BbButton);
//        BbButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                SoundPlayer.getInstance().playNote("audio\\a_1.wav");
//            }
//        });
//
//        JButton gButton = new JButton("G");
//        add(gButton);
//        gButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                SoundPlayer.getInstance().playNote("audio\\g_0.wav");
//            }
//        });
//
//        JButton chordButton = new JButton("Chord");
//        add(chordButton);
//        chordButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                SoundPlayer.getInstance().playChord(new Fretting(6, 8, 6, 6, 6, -1));
//            }
//        });

//        Fretting fretting1 = new Fretting(-1, 3, 2, 0, 1, 0);
//        Fretting fretting2 = new Fretting(6, 8, 6, 6, 6, 6);
//        FrettingView view1 = new FrettingView(fretting1);
//        FrettingView view2 = new FrettingView(fretting2);
//        FrettingViewGrid gridView = new FrettingViewGrid();

//        view.setBounds(0, 0, 400, 400);
//        add(view1);
//        add(view2);
//        view.repaint();


//        setLayout(new GridLayout(3,3));

        JPanel paddingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        paddingPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel contentPanel = new JPanel(new GridLayout(3, 3));

        QuizPanel quizPanel = new QuizPanel();
        contentPanel.add(quizPanel,2,0);

        paddingPanel.add(contentPanel);

        add(paddingPanel);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Main();
    }
}