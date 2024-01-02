import core.SoundPlayer;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    public Main() {
        JButton BbButton = new JButton("Bb");
        add(BorderLayout.WEST, BbButton);
        BbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundPlayer.getInstance().play("audio\\a_1.wav");
            }
        });

        JButton gButton = new JButton("G");
        add(BorderLayout.EAST, gButton);
        gButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundPlayer.getInstance().play("audio\\g_0.wav");
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