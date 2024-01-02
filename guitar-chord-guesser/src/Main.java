import core.Fretting;
import core.SoundPlayer;
import ui.FrettingView;
import ui.FrettingViewGrid;
import ui.QuizPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JPanel paddingPanel;
    private JPanel contentPanel;
    private QuizPanel quizPanel;

    public Main() {
        /* contentPanel */
        contentPanel = new JPanel(new GridLayout());
        quizPanel = new QuizPanel();

        contentPanel.add(quizPanel);
        /* --------------------------------------------- */

        /* paddingPanel */
        int paddingAmount = 30;
        paddingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, paddingAmount, paddingAmount));
        paddingPanel.setBorder(BorderFactory.createEmptyBorder(paddingAmount, paddingAmount, paddingAmount, paddingAmount));
        paddingPanel.add(contentPanel);
        /* --------------------------------------------- */

        /* this frame */
        add(paddingPanel);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Main();
    }
}