package core;

public class Fretting {
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

    public int[] getFretNumbers() {
        return fretNumbers;
    }

    public void setFretNumbers(int[] fretNumbers) {
        this.fretNumbers = fretNumbers;
    }

    public int getBarreStart() {
        return barreStart;
    }

    public void setBarreStart(int barreStart) {
        this.barreStart = barreStart;
    }

    public int getBarreEnd() {
        return barreEnd;
    }

    public void setBarreEnd(int barreEnd) {
        this.barreEnd = barreEnd;
    }
}
