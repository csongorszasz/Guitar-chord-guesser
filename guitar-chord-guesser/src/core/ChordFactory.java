package core;

public interface ChordFactory {
    Chord createSimpleChord(String base, Fretting fretting) throws Exception;
    Chord createBarreChord(String base, Fretting fretting) throws Exception;
}
