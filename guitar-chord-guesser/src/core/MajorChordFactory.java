package core;

public class MajorChordFactory implements ChordFactory {
    @Override
    public Chord createSimpleChord(String base, Fretting fretting) throws Exception {
        if (fretting.isBarre()) {
            throw new Exception("Expected normal fretting, not barre fretting");
        }
        return new Chord(base, Quality.MAJOR, fretting);
    }

    @Override
    public Chord createBarreChord(String base, Fretting fretting) throws Exception {
        if (!fretting.isBarre()) {
            throw new Exception("Expected barre fretting, not normal fretting");
        }
        return new Chord(base, Quality.MAJOR, fretting);
    }
}
