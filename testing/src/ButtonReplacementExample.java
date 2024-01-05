import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonReplacementExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Button Replacement Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        JButton oldButton = new JButton("Old Button");
        JButton newButton = new JButton("New Button");

        // Add action listener to old button
        oldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace oldButton with newButton
                Container contentPane = frame.getContentPane();
                contentPane.remove(oldButton);
                contentPane.add(newButton);
                contentPane.revalidate();
                contentPane.repaint();
            }
        });

        frame.add(oldButton);
        frame.setVisible(true);
    }
}
