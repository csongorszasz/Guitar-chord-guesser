package ui;

import logic.Fretting;
import logic.QuizManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuizOptionsGridPanel extends JPanel {
    private final int cols;
    private QuizManager quizManager;
    private FrettingView[] cells;
    private Color originalBg;

    public QuizOptionsGridPanel(QuizManager quizManager) {
        this.quizManager = quizManager;
        this.quizManager.setQuizOptionsGridPanel(this);
        cols = quizManager.getNumOfOptions();
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        originalBg = getBackground();
    }

    public void update() {
        removeAll();
        cells = new FrettingView[cols];
        for (int i = 0; i < cols; i++) {
            cells[i] = new FrettingView(quizManager.getChosenChords()[i].getFretting());
            cells[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));

            final int idx = i;
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
                    cells[idx].setBorder(BorderFactory.createLineBorder(new Color(100, 100, 251, 255), 5));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    setCursor(Cursor.getDefaultCursor());
                    cells[idx].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
            });

            add(cells[i]);
        }
    }
}
