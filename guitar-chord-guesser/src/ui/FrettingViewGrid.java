package ui;

import core.Fretting;

import javax.swing.*;
import java.awt.*;

public class FrettingViewGrid extends JPanel {
    private int stringDistance;
    private int fretDistance;
    private int stringThickness;

    public FrettingViewGrid() {
        setLayout(null);
        setPreferredSize(new Dimension(200, 250));

        stringDistance = getPreferredSize().width / 6;
        fretDistance = getPreferredSize().height / 5;
        stringThickness = 2;

        setBorder(BorderFactory.createMatteBorder(stringThickness*3, stringThickness, stringThickness, stringThickness, Color.BLACK));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < 6; i++) {
            // draw vertical line
            g.setColor(Color.BLACK);
            g.fillRect(i * stringDistance, 0, stringThickness, getPreferredSize().height);

            // draw horizontal line
            g.setColor(Color.GRAY);
            g.fillRect(0, i * fretDistance, getPreferredSize().width, 1);
        }
    }

//    public Point getFretCoordinates(int stringIdx, int fretIdx) {
//
//    }
}