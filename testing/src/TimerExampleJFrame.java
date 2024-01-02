import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerExampleJFrame extends JFrame {
    private final JLabel label;
    private int value;
    public TimerExampleJFrame() {
        label = new JLabel(Integer.toString(value));
        setLayout(new FlowLayout());
        add(label);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                value++;
                label.setText(Integer.toString(value));
            }
        });
        timer.start();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100,100,500,500);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TimerExampleJFrame();
    }
}
