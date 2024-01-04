package logic;

public class Chord {
    private String base;
    private String quality;
    private Fretting fretting;

    public Chord(String base, String quality, Fretting fretting) {
        this.base = base;
        this.quality = quality;
        this.fretting = fretting;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getBase() {
        return base;
    }

    public String getQuality() {
        return quality;
    }

    public Fretting getFretting() {
        return fretting;
    }

    @Override
    public String toString() {
        return base + quality;
    }
}
