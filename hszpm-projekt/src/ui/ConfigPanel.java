package ui;

import core.Settings;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel {
    private ChordSelectorPanel chordSelectorPanel;
    private SelectedChordPanel selectedChordPanel;
    private SongPropertiesPanel songPropertiesPanel;

    public ConfigPanel() {
        setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width / 4, -1));
        setLayout(new GridLayout(3, 1));

        chordSelectorPanel = new ChordSelectorPanel();
        selectedChordPanel = new SelectedChordPanel(chordSelectorPanel);
        songPropertiesPanel = new SongPropertiesPanel();

        add(chordSelectorPanel);
        add(selectedChordPanel);
        add(songPropertiesPanel);
    }
}
