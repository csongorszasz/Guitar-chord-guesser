package ui;

import core.Fretting;

import javax.swing.*;
import java.awt.*;

public class FrettingView extends JPanel {
    private Fretting fretting;
    private int startingFret;
    private JLabel startingFretLabel;

    public FrettingView(Fretting fretting) {
        setLayout(null);

        this.fretting = fretting;
    }
}
