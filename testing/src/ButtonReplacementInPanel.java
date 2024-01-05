import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonReplacementInPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Button Replacement in JPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton oldButton = new JButton("Old Button");
        JButton newButton = new JButton("New Button");

        // Add action listener to old button
        oldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace oldButton with newButton
                panel.remove(oldButton);
                panel.add(newButton);
                panel.revalidate();
                panel.repaint();
            }
        });

        panel.add(oldButton);
        frame.add(panel);
        frame.setVisible(true);
    }
}
