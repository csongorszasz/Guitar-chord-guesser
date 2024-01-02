package ui;

import core.Fretting;
import core.SoundPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayChordButton extends JButton {


    public PlayChordButton() {
        JButton chordButton = new JButton("Chord");
        add(chordButton);
        chordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundPlayer.getInstance().playChord(new Fretting(6, 8, 6, 6, 6, -1));
            }
        });
    }
}
