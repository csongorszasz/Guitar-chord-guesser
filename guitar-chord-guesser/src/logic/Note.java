package logic;

public class Note {
    public final static String C      = "C";
    public final static String D      = "D";
    public final static String E      = "E";
    public final static String F      = "F";
    public final static String G      = "G";
    public final static String A      = "A";
    public final static String B      = "B";
    public final static String CSHARP = "C#";
    public final static String DSHARP = "D#";
    public final static String FSHARP = "F#";
    public final static String GSHARP = "G#";
    public final static String ASHARP = "A#";
    public final static String DFLAT  = "Db";
    public final static String EFLAT  = "Eb";
    public final static String GFLAT  = "Gb";
    public final static String AFLAT  = "Ab";
    public final static String BFLAT  = "Bb";

    public static String sharpToFlat(String name) {
        switch (name) {
            case CSHARP -> { return DFLAT; }
            case DSHARP -> { return EFLAT; }
            case FSHARP -> { return GFLAT; }
            case GSHARP -> { return AFLAT; }
            case ASHARP -> { return BFLAT; }
        }
        return name;
    }

    public static String flatToSharp(String name) {
        switch (name) {
            case DFLAT -> { return CSHARP; }
            case EFLAT -> { return DSHARP; }
            case GFLAT -> { return FSHARP; }
            case AFLAT -> { return GSHARP; }
            case BFLAT -> { return ASHARP; }
        }
        return name;
    }
}
