import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NestedCardLayoutExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Nested CardLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel mainPanel = new JPanel(new CardLayout());

        // First card layout
        JPanel card1 = new JPanel(new CardLayout());
        JLabel label1 = new JLabel("This is Card 1", SwingConstants.CENTER);
        JButton nextToCard2 = new JButton("Next to Card 2");

        card1.add(label1, "card1");
        card1.add(nextToCard2, "button1");

        nextToCard2.addActionListener(e -> {
            CardLayout layout = (CardLayout) card1.getLayout();
            layout.show(card1, "card2");
        });

        // Second card layout
        JPanel card2 = new JPanel(new CardLayout());
        JLabel label2 = new JLabel("This is Card 2", SwingConstants.CENTER);
        JButton nextToCard3 = new JButton("Next to Card 3");

        card2.add(label2, "card2");
        card2.add(nextToCard3, "button2");

        nextToCard3.addActionListener(e -> {
            CardLayout layout = (CardLayout) card2.getLayout();
            layout.show(card2, "card3");
        });

        // Third card layout
        JPanel card3 = new JPanel(new CardLayout());
        JLabel label3 = new JLabel("This is Card 3", SwingConstants.CENTER);
        JButton backToCard1 = new JButton("Back to Card 1");

        card3.add(label3, "card3");
        card3.add(backToCard1, "button3");

        backToCard1.addActionListener(e -> {
            CardLayout layout = (CardLayout) card1.getLayout();
            layout.show(card1, "card1");
        });

        // Add the nested panels to the main panel
        mainPanel.add(card1, "main1");
        mainPanel.add(card2, "main2");
        mainPanel.add(card3, "main3");

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
