package ui;

import core.Fretting;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class FrettingView extends JPanel {
    private FrettingViewGrid viewGrid;
    private Point gridPos;
    private Fretting fretting;
    private int startingFret;
    private JLabel startingFretLabel;

    public FrettingView(Fretting fretting) {
        setLayout(null);
        setPreferredSize(new Dimension(400, 400));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        viewGrid = new FrettingViewGrid();
        this.fretting = fretting;

        startingFret = Fretting.NUM_OF_FRETS;
        for (int fretNum : fretting.getFretNumbers()) {
            if (fretNum != -1 && fretNum < startingFret) {
                startingFret = fretNum;
            }
        }

        startingFretLabel = new JLabel(startingFret + "fr");

        gridPos = new Point(
                (this.getPreferredSize().width - viewGrid.getPreferredSize().width) / 2,
                (this.getPreferredSize().height - viewGrid.getPreferredSize().height) / 2
        );
        viewGrid.setBounds(
                gridPos.x,
                gridPos.y,
                viewGrid.getPreferredSize().width,
                viewGrid.getPreferredSize().height
        );
        add(viewGrid);
    }

    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);

        /* draw barre */
        /* ----------------------------------*/
        if (fretting.getBarreStart() != -1) {
            int barreLength = fretting.getBarreEnd() - fretting.getBarreStart() + 1;
            int barreOffsetX = 10;
            g.setColor(Color.BLACK);
            g.fillRect(
                    -barreOffsetX + gridPos.x + fretting.getBarreStart() * viewGrid.getStringDistance(),
                    gridPos.y + fretting.getBarreFret() * viewGrid.getFretDistance() - viewGrid.getFretDistance()/2,
                    barreLength * viewGrid.getStringDistance() + 2*barreOffsetX,
                    10
            );
        }

        /* draw fret marks (dots/x's/o's) */
        /* ----------------------------------*/
        int markSize = 15;
        int offsetY = 15;
        for (int i = 0; i < Fretting.NUM_OF_STRINGS; i++) {
            int fretNum = fretting.getFretNumbers()[i];
            if (fretNum == -1) {
                drawX(g, i, markSize, offsetY);
            } else if (fretNum == 0) {
                drawO(g, i, markSize, offsetY);
            } else {
                drawDot(g, i, fretNum, markSize, offsetY);
            }
        }
    }

    private void drawX(Graphics g, int stringNum, int size, int offsetY) {
        Point point = viewGrid.getFretCoordinates(stringNum, 0);
        g.setColor(Color.BLACK);
        g.drawLine(
                gridPos.x + point.x - size/2,
                gridPos.y + point.y - size/2 - offsetY,
                gridPos.x +point.x + size/2,
                gridPos.y + point.y + size/2 - offsetY
        );
        g.drawLine(
                gridPos.x + point.x + size/2,
                gridPos.y + point.y - size/2 - offsetY,
                gridPos.x + point.x - size/2,
                gridPos.y + point.y + size/2 - offsetY
        );
    }

    private void drawO(Graphics g, int stringNum, int size, int offsetY) {
        Point point = viewGrid.getFretCoordinates(stringNum, 0);
        g.setColor(Color.BLACK);
        size++; /* to match the size of the X */
        g.drawOval(
                gridPos.x + point.x - size/2,
                gridPos.y + point.y - size/2 - offsetY,
                size,
                size
        );
    }

    private void drawDot(Graphics g, int stringNum, int fretNum, int size, int offsetY) {
        Point point = viewGrid.getFretCoordinates(stringNum, fretNum);
        g.setColor(Color.BLACK);
        size++; /* to match the size of the X */
        g.fillOval(
                gridPos.x + point.x - size/2,
                gridPos.y + point.y - size/2 - offsetY,
                size,
                size
        );
    }
}
