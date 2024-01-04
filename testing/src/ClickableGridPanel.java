import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickableGridPanel extends JFrame {
    private static final int ROWS = 4;
    private static final int COLS = 4;

    public ClickableGridPanel() {
        setTitle("Clickable Grid Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        JPanel gridPanel = new JPanel(new GridLayout(ROWS, COLS));
        gridPanel.setPreferredSize(new Dimension(300, 300));

        // Create a two-dimensional array of JLabels representing grid cells
        JLabel[][] cells = new JLabel[ROWS][COLS];

        // Add mouse listener to each cell
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                cells[row][col] = new JLabel();
                cells[row][col].setOpaque(true);
                cells[row][col].setBackground(Color.WHITE);
                cells[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                final int r = row;
                final int c = col;
                cells[row][col].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("Clicked cell: " + r + ", " + c);
                        setCursor(Cursor.getDefaultCursor());
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        super.mouseEntered(e);
                        cells[r][c].setBackground(Color.GREEN);
                        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        super.mouseExited(e);
                        cells[r][c].setBackground(Color.WHITE);
                        setCursor(Cursor.getDefaultCursor());
                    }
                });

                gridPanel.add(cells[row][col]);
            }
        }

        add(gridPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClickableGridPanel());
    }
}
