package ui;

import core.Chord;

public class FrettingViewController {
    private Chord chord;
    private FrettingView frettingView;
    private int currentVariation;

    public FrettingViewController(Chord chord, FrettingView frettingView) {
        this.chord = chord;
        this.frettingView = frettingView;
        currentVariation = 0;

        // TODO:
        //  -
    }
}
