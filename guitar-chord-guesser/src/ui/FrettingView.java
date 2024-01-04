package ui;

import logic.Fretting;

import javax.swing.*;
import java.awt.*;

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

        startingFret = 1;
        startingFretLabel = new JLabel();
        startingFretLabel.setFont(new Font(startingFretLabel.getFont().getName(), Font.PLAIN, 30));
        startingFretLabel.setBounds(
                gridPos.x + viewGrid.getPreferredSize().width + 15,
                gridPos.y + viewGrid.getFretDistance()/2 - startingFretLabel.getFont().getSize()/2,
                50,
                30
        );
        add(startingFretLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        /* draw starting fret label */
        startingFret = Fretting.NUM_OF_FRETS;
        for (int fretNum : fretting.getFretNumbers()) {
            if (fretNum != -1 && fretNum < startingFret) {
                startingFret = fretNum;
            }
        }
        if (startingFret == 0) {
            startingFretLabel.setText("");
        } else {
            startingFretLabel.setText(startingFret + "fr");
        }
        /* ----------------------------------*/

        /* draw barre */
        if (fretting.getBarreStart() != -1) {
            drawBarre(g);
        }
        /* ----------------------------------*/

        /* draw fret marks (dots/x's/o's) */
        for (int i = 0; i < Fretting.NUM_OF_STRINGS; i++) {
            int fretNum = fretting.getFretNumbers()[i];
            if (fretNum == -1) {
                drawX(g, i, 15, 15);
            } else if (fretNum == 0) {
                drawO(g, i, 15, 15);
            } else if (fretNum != fretting.getBarreFret()) {
                drawDot(g, i, fretNum, 20);
            }
        }
        /* ----------------------------------*/
    }

    private void drawBarre(Graphics g) {
        int barreLength = fretting.getBarreEnd() - fretting.getBarreStart();
        int barreSize = 10;
        int barreOffsetX = 10;
        Point point = viewGrid.getFretCoordinates(fretting.getBarreStart(), fretting.getBarreFret()-(startingFret-1));
        g.setColor(Color.BLACK);
        g.fillRect(
                -barreOffsetX + gridPos.x + point.x,
                gridPos.y + point.y - viewGrid.getFretDistance()/2 - barreSize/2,
                barreLength * viewGrid.getStringDistance() + 2*barreOffsetX,
                barreSize
        );
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

    private void drawDot(Graphics g, int stringNum, int fretNum, int size) {
        Point point = viewGrid.getFretCoordinates(stringNum, fretNum-(startingFret-1));
        g.setColor(Color.BLACK);
        g.fillOval(
                gridPos.x + point.x - size/2,
                gridPos.y + point.y - viewGrid.getFretDistance()/2 - size/2,
                size,
                size
        );
    }
}
