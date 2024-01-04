package logic;

public class Fretting {
    public static final int DONT_PLAY = -1;
    public static final int OPEN_STRING = 0;
    public static final int NUM_OF_STRINGS = 6;
    public static final int NUM_OF_FRETS = 20;
    public static final int NUM_OF_FRETS_PER_VIEW = 5;
    public static final int STRING_E1 = 0;
    public static final int STRING_A = 1;
    public static final int STRING_D = 2;
    public static final int STRING_G = 3;
    public static final int STRING_B = 4;
    public static final int STRING_E2 = 5;
    public static String[] stringNamesEncoded = {"e1", "a", "d", "g", "b", "e2"};

    private int[] fretNumbers;
    private int barreStart;
    private int barreEnd;
    private int barreFret;

    public Fretting(int e1, int a, int d, int g, int b, int e2, int barreStart, int barreEnd, int barreFret) throws Exception {
        if (
                e1 < DONT_PLAY || e1 > NUM_OF_FRETS ||
                a < DONT_PLAY || a > NUM_OF_FRETS ||
                d < DONT_PLAY || d > NUM_OF_FRETS ||
                g < DONT_PLAY || g > NUM_OF_FRETS ||
                b < DONT_PLAY || b > NUM_OF_FRETS ||
                e2 < DONT_PLAY || e2 > NUM_OF_FRETS ||
                barreFret < DONT_PLAY || barreFret > NUM_OF_FRETS
        ) {
            throw new Exception("Fret numbers out of bounds!");
        } else if (
                barreStart < -1 || barreStart >= NUM_OF_STRINGS ||
                barreEnd < -1 || barreEnd >= NUM_OF_STRINGS
        ) {
            throw new Exception("Barre interval out of bounds!");
        }

        fretNumbers = new int[6];
        fretNumbers[0] = e1;
        fretNumbers[1] = a;
        fretNumbers[2] = d;
        fretNumbers[3] = g;
        fretNumbers[4] = b;
        fretNumbers[5] = e2;
        this.barreStart = barreStart;
        this.barreEnd = barreEnd;
        this.barreFret = barreFret;
    }

    public Fretting(int e1, int a, int d, int g, int b, int e2) throws Exception {
        if (
                e1 < DONT_PLAY || e1 > NUM_OF_FRETS ||
                a < DONT_PLAY || a > NUM_OF_FRETS ||
                d < DONT_PLAY || d > NUM_OF_FRETS ||
                g < DONT_PLAY || g > NUM_OF_FRETS ||
                b < DONT_PLAY || b > NUM_OF_FRETS ||
                e2 < DONT_PLAY || e2 > NUM_OF_FRETS
        ) {
            throw new Exception("Fret numbers out of bounds!");
        }

        fretNumbers = new int[6];
        fretNumbers[0] = e1;
        fretNumbers[1] = a;
        fretNumbers[2] = d;
        fretNumbers[3] = g;
        fretNumbers[4] = b;
        fretNumbers[5] = e2;
        this.barreStart = -1;
        this.barreEnd = -1;
        this.barreFret = -1;
    }

    public Fretting() {
        fretNumbers = new int[6];
        fretNumbers[0] = -1;
        fretNumbers[1] = -1;
        fretNumbers[2] = -1;
        fretNumbers[3] = -1;
        fretNumbers[4] = -1;
        fretNumbers[5] = -1;
        this.barreStart = -1;
        this.barreEnd = -1;
        this.barreFret = -1;
    }

    public int[] getFretNumbers() {
        return fretNumbers;
    }

    public int getBarreStart() {
        return barreStart;
    }

    public int getBarreEnd() {
        return barreEnd;
    }

    public int getBarreFret() {
        return barreFret;
    }

    public boolean isBarre() {
        return barreStart != -1 && barreEnd != -1 && barreFret != -1;
    }
}
