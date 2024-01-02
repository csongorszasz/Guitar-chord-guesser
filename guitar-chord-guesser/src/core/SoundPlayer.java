package core;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SoundPlayer {
    public static SoundPlayer instance;
    private Map<String, Clip> clips;
    private int strumDelay; // in milliseconds

    SoundPlayer() {
        clips = new HashMap<String, Clip>();
        loadAllAudioFiles();
        strumDelay = 250;
    }

    public static SoundPlayer getInstance() {
        if (instance == null) {
            instance = new SoundPlayer();
        }
        return instance;
    }

    public void playNote(String filepath) {
        Clip clip = clips.get(filepath);
        clip.setFramePosition(0);
        clip.start();
    }

    public void playChord(Fretting fretting) {
        for (int i = 0; i < fretting.getFretNumbers().length; i++) {
            int fretNumber = fretting.getFretNumbers()[i];
            if (fretNumber != -1) {
                String filepath = "audio\\" + Fretting.stringNames[i] + "_" + fretNumber + ".wav";
                SoundPlayer.getInstance().playNote(filepath);
            }
        }
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
