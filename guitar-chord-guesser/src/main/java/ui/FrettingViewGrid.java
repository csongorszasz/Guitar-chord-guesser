package main.java.ui;

import main.java.logic.Fretting;

import javax.swing.*;
import java.awt.*;

public class FrettingViewGrid extends JPanel {
    private final int stringDistance;
    private final int fretDistance;
    private final int stringThickness;

    public FrettingViewGrid() {
        setLayout(null);
        setPreferredSize(new Dimension(200, 200));

        stringDistance = getPreferredSize().width / (Fretting.NUM_OF_STRINGS-1);
        fretDistance = getPreferredSize().height / Fretting.NUM_OF_FRETS_PER_VIEW;
        stringThickness = 2;

        setBorder(BorderFactory.createMatteBorder(stringThickness*4, stringThickness, stringThickness, stringThickness, Color.BLACK));
    }

    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);

        for (int i = 1; i < Fretting.NUM_OF_STRINGS; i++) {
            // draw vertical line
            g.setColor(Color.BLACK);
            g.fillRect(i * stringDistance, 0, stringThickness, getPreferredSize().height);

            // draw horizontal line
            g.setColor(Color.GRAY);
            g.fillRect(0, i * fretDistance, getPreferredSize().width, 1);
        }
    }

    public Point getFretCoordinates(int stringIdx, int fretIdx) {
        return new Point(stringIdx * stringDistance, fretIdx * fretDistance);
    }

    public int getStringDistance() {
        return stringDistance;
    }

    public int getFretDistance() {
        return fretDistance;
    }

    public int getStringThickness() {
        return stringThickness;
    }
}
