package main.java.logic;

public class DiminishedChordFactory implements ChordFactory {
    @Override
    public Chord createNormalChord(String base, Fretting fretting) throws Exception {
        if (fretting.isBarre()) {
            throw new Exception("Expected normal fretting, not barre fretting");
        }
        Chord chord = new Chord(base, Quality.DIMINISHED, fretting);
        System.out.println("Created normal chord: " + chord);
        return chord;
    }

    @Override
    public Chord createBarreChord(String base, Fretting fretting) throws Exception {
        if (!fretting.isBarre()) {
            throw new Exception("Expected barre fretting, not normal fretting");
        }
        Chord chord = new Chord(base, Quality.DIMINISHED, fretting);
        System.out.println("Created barre chord: " + chord);
        return chord;
    }
}
