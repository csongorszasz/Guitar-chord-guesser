package ui;

import logic.Fretting;
import logic.QuizManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuizOptionsGridPanel extends JPanel {
    private final int cols;
    private QuizManager quizManager;
    private FrettingView[] cells;

    public QuizOptionsGridPanel(QuizManager quizManager) {
        this.quizManager = quizManager;
        cols = quizManager.getNumOfOptions();
        setLayout(new GridLayout(1, cols));

        cells = new FrettingView[cols];
        for (int i = 0; i < cols; i++) {
            cells[i] = new FrettingView(new Fretting());
            cells[i].setBorder(BorderFactory.createLineBorder(Color.BLUE));

            cells[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    setCursor(Cursor.getDefaultCursor());
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    setCursor(Cursor.getDefaultCursor());
                }
            });

            add(cells[i]);
        }
    }

    public void update() {
        for (int i = 0; i < cols; i++) {
            cells[i] = new FrettingView(quizManager.getChosenChords()[i].getFretting());
            cells[i].setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }
        repaint();
    }
}
