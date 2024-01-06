package logic;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SoundPlayer {
    private Random random;
    public static SoundPlayer instance;
    private Map<String, Clip> noteClips;
    private Map<String, Clip> musicClips;
    private int strumDelay; // in milliseconds
    private String currentlyPlayingSong;

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
                String filepath = "audio\\notes\\" + Fretting.stringNamesEncoded[currentStringIdx] + "_" + fretNumber + ".wav";

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
        random = new Random();

        noteClips = new HashMap<String, Clip>();
        musicClips = new HashMap<String, Clip>();
        loadAllAudioFiles();

        strumDelay = 100;
        currentlyPlayingSong = "";
    }

    public static SoundPlayer getInstance() {
        if (instance == null) {
            instance = new SoundPlayer();
        }
        return instance;
    }

    public boolean playNote(String filepath) {
        Clip clip = noteClips.get(filepath);

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

    public void playRandomSong() {
        int songIdx = random.nextInt(musicClips.size());
        int i = 0;
        for (String filepath : musicClips.keySet()) {
            if (i == songIdx) {
                playSong(filepath);
                break;
            } else {
                i++;
            }
        }
    }

    public void playSong(String filepath) {
        Clip clip = musicClips.get(filepath);
        currentlyPlayingSong = filepath;
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }

    public void stopCurrentlyPlayingSong() {
        Clip clip = musicClips.get(currentlyPlayingSong);
        clip.stop();
    }

    private void loadAllAudioFiles() {
        loadNoteSoundFiles();
        loadMusicFiles();
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

    private void loadNoteSoundFiles() {
        File[] guitarSoundFiles = new File("audio\\notes").listFiles();
        if (guitarSoundFiles != null) {
            for (File file : guitarSoundFiles) {
                Clip clip = loadAudioFile(file);
                noteClips.put(file.getPath(), clip);
            }
        }
    }

    private void loadMusicFiles() {
        File[] guitarMusicFiles = new File("audio\\music").listFiles();
        if (guitarMusicFiles != null) {
            for (File file : guitarMusicFiles) {
                Clip clip = loadAudioFile(file);
                musicClips.put(file.getPath(), clip);
            }
        }
    }
}
