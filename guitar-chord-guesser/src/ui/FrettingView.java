package ui;

import core.Fretting;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class FrettingView extends JPanel {
    private FrettingViewGrid viewGrid;
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

        viewGrid.setBounds(
                (this.getPreferredSize().width - viewGrid.getPreferredSize().width) / 2,
                (this.getPreferredSize().height - viewGrid.getPreferredSize().height) / 2,
                viewGrid.getPreferredSize().width,
                viewGrid.getPreferredSize().height
        );
        add(viewGrid);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < Fretting.NUM_OF_STRINGS; i++) {

        }
    }
}
