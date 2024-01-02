import core.Fretting;
import core.SoundPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    public Main() {
        JButton BbButton = new JButton("Bb");
        add(BorderLayout.WEST, BbButton);
        BbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundPlayer.getInstance().playNote("audio\\a_1.wav");
            }
        });

        JButton gButton = new JButton("G");
        add(BorderLayout.EAST, gButton);
        gButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundPlayer.getInstance().playNote("audio\\g_0.wav");
            }
        });

        JButton chordButton = new JButton("Chord");
        add(BorderLayout.CENTER, chordButton);
        chordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundPlayer.getInstance().playChord(new Fretting(-1, 3, 2, 0, 1, 0));
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(500,500,300,300);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Main();
    }
}