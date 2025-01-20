package main.java.ui;

import main.java.logic.QuizManager;

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
        this.quizManager.setQuizOptionsGridPanel(this);
        cols = quizManager.getNumOfOptions();
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
    }

    public void updateGrid() {
        removeAll();
        cells = new FrettingView[cols];
        for (int i = 0; i < cols; i++) {
            cells[i] = new FrettingView(quizManager.getChosenChords()[i].getFretting());
            cells[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));

            final int idx = i;
            cells[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (quizManager.getPhase() != QuizManager.PHASE_QUESTION) {
                        return;
                    }

                    super.mouseClicked(e);
                    setCursor(Cursor.getDefaultCursor());
                    quizManager.answered(idx);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (quizManager.getPhase() != QuizManager.PHASE_QUESTION) {
                        return;
                    }

                    super.mouseEntered(e);
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    setCellBorder(idx, new Color(100, 100, 251, 255), 5);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (quizManager.getPhase() != QuizManager.PHASE_QUESTION) {
                        return;
                    }

                    super.mouseExited(e);
                    setCursor(Cursor.getDefaultCursor());
                    setCellBorder(idx, Color.BLACK, 1);
                }
            });

            add(cells[i]);
        }
    }

    public void setCellBorder(int cellIdx, Color color, int thickness) {
        cells[cellIdx].setBorder(BorderFactory.createLineBorder(color, thickness));
    }
}
