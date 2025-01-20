package main.java.ui;

import main.java.logic.QuizManager;
import main.java.logic.SoundPlayer;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel paddingPanel;
    private JPanel contentPanel;
    private QuizManager quizManager;
    private QuizPanel quizPanel;
    private MainMenuPanel mainMenuPanel;
    private StatisticsPanel statisticsPanel;

    public static String VIEW_QUIZ = "QUIZ";
    public static String VIEW_MAINMENU = "MAINMENU";
    public static String VIEW_STATISTICS = "STATISTICS";
    private CardLayout layout;
    private JPanel cardPanel;

    public MainFrame() {
        /* cardPanel */
        layout = new CardLayout();
        cardPanel = new JPanel(layout);

        quizManager = new QuizManager();
        mainMenuPanel = new MainMenuPanel(this, quizManager);
        statisticsPanel = new StatisticsPanel(this, quizManager);
        mainMenuPanel.setStatisticsPanel(statisticsPanel);
        quizPanel = new QuizPanel(this, quizManager);

        cardPanel.add(quizPanel, VIEW_QUIZ);
        cardPanel.add(mainMenuPanel, VIEW_MAINMENU);
        cardPanel.add(statisticsPanel, VIEW_STATISTICS);
        /* --------------------------------------------- */

        /* this frame */
        add(BorderLayout.CENTER, cardPanel);
        layout.show(cardPanel, VIEW_MAINMENU);

        SoundPlayer.getInstance().playRandomSong();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        /* --------------------------------------------- */
    }

    public void showLayout(String name) {
        layout.show(cardPanel, name);
    }
}