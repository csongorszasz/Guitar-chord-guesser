package core;

public class Fretting {
    public static final int DONT_PLAY = -1;
    public static final int OPEN_STRING = 0;

    public static String[] stringNames = {"e1", "a", "d", "g", "b", "e2"};
    private int[] fretNumbers;
    private int barreStart;
    private int barreEnd;

    public Fretting(int e1, int a, int d, int g, int b, int e2, int barreStart, int barreEnd) {
        fretNumbers = new int[6];
        fretNumbers[0] = e1;
        fretNumbers[1] = a;
        fretNumbers[2] = d;
        fretNumbers[3] = g;
        fretNumbers[4] = b;
        fretNumbers[5] = e2;
        this.barreStart = barreStart;
        this.barreEnd = barreEnd;
    }

    public Fretting(int e1, int a, int d, int g, int b, int e2) {
        fretNumbers = new int[6];
        fretNumbers[0] = e1;
        fretNumbers[1] = a;
        fretNumbers[2] = d;
        fretNumbers[3] = g;
        fretNumbers[4] = b;
        fretNumbers[5] = e2;
        this.barreStart = -1;
        this.barreEnd = -1;
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
}
