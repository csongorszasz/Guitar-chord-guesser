import core.Fretting;
import core.SoundPlayer;
import ui.FrettingView;
import ui.FrettingViewGrid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    public Main() {
        setLayout(new FlowLayout());
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

        Fretting fretting1 = new Fretting(-1, 3, 2, 0, 1, 0);
        Fretting fretting2 = new Fretting(6, 8, 6, 6, 6, 6);
//        FrettingView view = new FrettingView(fretting1);
        FrettingViewGrid gridView = new FrettingViewGrid();

        add(gridView);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(500,500,300,300);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Main();
    }
}