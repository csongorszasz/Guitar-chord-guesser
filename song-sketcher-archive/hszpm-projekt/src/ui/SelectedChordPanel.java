package ui;

import core.Fretting;

import javax.swing.*;
import java.awt.*;

public class SelectedChordPanel extends JPanel {
    private ChordSelectorPanel chordSelectorPanel;
    private FrettingView frettingView;
    private FrettingViewController frettingViewController;
    private JButton playButton;

    public SelectedChordPanel(ChordSelectorPanel chordSelectorPanel) {
        setLayout(new GridBagLayout());

        this.chordSelectorPanel = chordSelectorPanel;
        frettingView = new FrettingView(new Fretting(0, 0, 0, 0, 0, 0));
        frettingViewController = new FrettingViewController(this.chordSelectorPanel.getChord(), frettingView);
        playButton = new JButton("+");

//        GridBagConstraints c = new GridBagConstraints();

        // TODO:
        //  - add frettingView
        //  - add play button and add functionality
    }
}
