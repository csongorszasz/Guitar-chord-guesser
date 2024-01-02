package core;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SoundPlayer {
    public static SoundPlayer instance;
    private Map<String, Clip> clips;

    SoundPlayer() {
        clips = new HashMap<String, Clip>();
        loadAllAudioFiles();
    }

    public static SoundPlayer getInstance() {
        if (instance == null) {
            instance = new SoundPlayer();
        }
        return instance;
    }

    public void play(String filepath) {
        Clip clip = clips.get(filepath);
        clip.setFramePosition(0);
        clip.start();
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
