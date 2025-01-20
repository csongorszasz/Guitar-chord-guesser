package main.java.logic;

public interface ChordFactory {
    Chord createNormalChord(String base, Fretting fretting) throws Exception;
    Chord createBarreChord(String base, Fretting fretting) throws Exception;
}
