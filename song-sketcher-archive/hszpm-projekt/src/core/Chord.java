package core;

import java.util.ArrayList;

public class Chord {
    private Note base;
    private Quality quality;
    private ArrayList<Fretting> variations;

    public Chord(Note base, Quality quality, ArrayList<Fretting> variations) {
        this.base = base;
        this.quality = quality;
        this.variations = variations;
    }

    public Note getBase() {
        return base;
    }

    public void setBase(Note base) {
        this.base = base;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public ArrayList<Fretting> getVariations() {
        return variations;
    }

    public void setVariations(ArrayList<Fretting> variations) {
        this.variations = variations;
    }
}
