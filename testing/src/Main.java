import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main extends JFrame {
    public Main() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        setExtendedState(MAXIMIZED_BOTH);
        setBounds(500, 500, 300, 300);
        setLayout(new GridBagLayout());
//        setLayout(new GridLayout());

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width / 4, -1));
//        panel.setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width / 4, -1));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        String[] names = {"One", "Two", "Three", "Other"};
        JComboBox comboBox = new JComboBox(names);
        comboBox.setSelectedIndex(0);
        panel.add(new LabeledComponent("Komboboksz", comboBox));

        comboBox = new JComboBox(names);
        comboBox.setSelectedIndex(3);
        panel.add(new LabeledComponent("Boksz", comboBox));

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.25;
        add(panel, c);
//        add(panel);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.75;
        add(new JPanel(), c);
//        add(new JPanel());

        setVisible(true);
    }

    public static void main(String[] args) {
//        new Main();
        Random random = new Random();
        int quizModes = 7;
        int questions = 10;
        for (int i = 0; i < 1000; i++) {
            int randQuizMode = random.nextInt(quizModes);
            int randCorrectAnswers = random.nextInt(questions+1);
            System.out.println(randQuizMode + " " + randCorrectAnswers);
        }
    }
}
