package core;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SoundPlayer {
    public static SoundPlayer instance;
    private Map<String, Clip> clips;
    private int strumDelay; // in milliseconds

    private class StringPlayActionListener implements ActionListener {
        private int currentStringIdx = 0;
        private Fretting fretting;
        private Timer timer;

        public StringPlayActionListener(Fretting fretting, Timer timer) {
            currentStringIdx = 0;
            this.fretting = fretting;
            this.timer = timer;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // if the current string need not be played, then jump to the next string, if it's not the last string
            while (currentStringIdx < 6 && (fretting.getFretNumbers()[currentStringIdx] == Fretting.DONT_PLAY)) {
                currentStringIdx++;
            }

            // if it's the last string, then stop, else play the current string's sound
            if (currentStringIdx == 6) {
                timer.stop();
            }
            else {
                int fretNumber = fretting.getFretNumbers()[currentStringIdx];
                String filepath = "audio\\" + Fretting.stringNamesEncoded[currentStringIdx] + "_" + fretNumber + ".wav";

                if (!playNote(filepath)) {
                    timer.stop();
                }
                else {
                    currentStringIdx++;
                }
            }
        }
    }

    SoundPlayer() {
        clips = new HashMap<String, Clip>();
        loadAllAudioFiles();
        strumDelay = 100;
    }

    public static SoundPlayer getInstance() {
        if (instance == null) {
            instance = new SoundPlayer();
        }
        return instance;
    }

    public boolean playNote(String filepath) {
        Clip clip = clips.get(filepath);

        // it the note is already playing, return false
        if ((0 < clip.getFramePosition()) && (clip.getFramePosition() < clip.getFrameLength())) {
            return false;
        }

        // else play the sound
        clip.setFramePosition(0);
        clip.start();
        return true;
    }

    public void playChord(Fretting fretting) {
        Timer timer = new Timer(strumDelay, null);
        timer.addActionListener(new StringPlayActionListener(fretting, timer));
        timer.start();
    }

    private void loadAllAudioFiles() {
        File[] guitarSoundFiles = new File("audio").listFiles();
        if (guitarSoundFiles != null) {
            for (File file : guitarSoundFiles) {
                Clip clip = loadAudioFile(file);
                clips.put(file.getPath(), clip);
            }
        }
    }

    private Clip loadAudioFile(File file) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
        return null;
    }
}
